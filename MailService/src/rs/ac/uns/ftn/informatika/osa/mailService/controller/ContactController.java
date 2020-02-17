package rs.ac.uns.ftn.informatika.osa.mailService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.mailService.MailServiceApplication;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.AccountDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.ContactDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.PhotoDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Photo;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AccountServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.ContactService;
import rs.ac.uns.ftn.informatika.osa.mailService.service.ContactServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.EvenLogServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.PhotoServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.UserServiceInterface;

@RestController
@RequestMapping(value="/contacts")
public class ContactController {

	@Autowired
	private ContactServiceInterface contactService;
	@Autowired
	private AccountServiceInterface accountService;
	@Autowired
	private PhotoServiceInterface photoService;
	@Autowired
	private EvenLogServiceInterface logService;
	@Autowired
	private UserServiceInterface userService;

	@GetMapping
	public ArrayList<ContactDTO> getAllContacts(@RequestParam("userId") int userId){
		ArrayList<ContactDTO> ret = new ArrayList<ContactDTO>();
		List<Contact> con = new ArrayList<Contact>();
		Account loggedIn=null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				loggedIn=a;
				con =  a.getUser().getUserContacts();
			}
		}
		for(Contact m : con) {
			System.out.println("kontakt usera "+ m.getFirstName());
			PhotoDTO p=new PhotoDTO();
			if(m.getPhoto()!=null)
				p=new PhotoDTO(m.getPhoto(),m);
			ret.add(new ContactDTO(m,p));
		}
		return ret;
	}
	
	@PostMapping("/add")
	public ContactDTO createContact(@RequestBody() ContactDTO contact, @RequestParam("userId") int userId) {
		System.out.println("dodavanje kontakta");
		MailServiceApplication.eventLog.setLog("Dodavanje kontakta \n");
		logService.save(MailServiceApplication.eventLog);
		
		AccountDTO user=null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				user=new AccountDTO(a);
				PhotoDTO photoDTO=new PhotoDTO();
				photoDTO.setData(contact.getPhoto().getData());
				photoDTO.setPath(contact.getPhoto().getPath());
				contact.setPhoto(photoDTO);
				user.getContacts().add(contact);
				
				Photo photo= new Photo(photoDTO.getPath(),photoDTO.getData());
				Contact newContact=new Contact(contact.getFirstName(), contact.getLastName(), contact.getFirstName(), 
						contact.getEmail(), "", photo, a.getUser());
				photoService.save(photo);
				photo.setContact(newContact);
				a.getUser().getUserContacts().add(newContact);
				accountService.save(a);
				contactService.save(newContact);
				photoService.save(photo);
				
				System.out.println("dodavanje kontakta entity "+ newContact.getFirstName());
				for (Contact c : a.getUser().getUserContacts()) {
					System.out.println(c.getFirstName());
				}
				return contact;
			}
		}
		return contact;
	}
	
	@PutMapping("/update")
	public ContactDTO updateContact(@RequestBody() ContactDTO contactDTO, @RequestParam("userId") int id ) {
		MailServiceApplication.eventLog.setLog("Izmena kontakta \n");
		logService.save(MailServiceApplication.eventLog);
		
		for(Account acc : accountService.findAll()) {
			if(acc.getId() == id) {
				for(Contact con : acc.getUser().getUserContacts()) {
					if(con.getId() == contactDTO.getId()) {
						con.setFirstName(contactDTO.getFirstName());
						con.setLastName(contactDTO.getLastName());
						con.setEmail(contactDTO.getEmail());
						if(contactDTO.getPhoto()!=null) {
							if(con.getPhoto()==null)
								con.setPhoto(new Photo());
							con.getPhoto().setData(contactDTO.getPhoto().getData());
							con.getPhoto().setPath(contactDTO.getPhoto().getPath());
							photoService.save(con.getPhoto());
						}
						
						contactService.save(con);
						
						System.out.println("PHOTO URL "+ contactDTO.getPhoto().getData());
						//TODO: update photo
						return contactDTO;
					}
				}
			}
		}
		
		return null;
	}
	
	@DeleteMapping("/delete")
	public ArrayList<ContactDTO> deleteContact(@RequestParam("contactId") int id, @RequestParam("userId") int userId) {
		System.out.println("contacts delete");
		MailServiceApplication.eventLog.setLog("Brisanje kontakta \n");
		logService.save(MailServiceApplication.eventLog);
		User loggedIn = userService.findOne(userId);
		
		Account acc = accountService.findOne(2);
		Contact con = null;
		for (Contact c : loggedIn.getUserContacts()) {
			if(c.getId() == id) {
				con = c;
			}
		}
		if(con != null) {
			loggedIn.getUserContacts().remove(con);
			if(con.getPhoto()!=null) {
				con.getPhoto().setContact(null);
				int idPhotoDelete=con.getPhoto().getId();
				con.setPhoto(null);
				photoService.remove(idPhotoDelete);
			}
			
			contactService.remove(con.getId());
			accountService.save(acc);
		}
		
		ArrayList<ContactDTO> ret = new ArrayList<ContactDTO>();
		List<Contact> cons = acc.getUser().getUserContacts();
		for(Contact m : cons) {
			PhotoDTO p=new PhotoDTO();
			if(m.getPhoto()!=null)
				p=new PhotoDTO(m.getPhoto(),m);
			ret.add(new ContactDTO(m,p));
		}
		return ret;
	}

}
