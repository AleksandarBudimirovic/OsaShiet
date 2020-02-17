package rs.ac.uns.ftn.informatika.osa.mailService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.mailService.MailServiceApplication;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.AccountDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.FolderDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.UserDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AccountServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.EvenLogServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.FolderServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.MessageServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.UserServiceInterface;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountServiceInterface accountService;
	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private EvenLogServiceInterface logService;
	@Autowired
	private FolderServiceInterface foderService;
	
	@PostMapping("/login")
	public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
		MailServiceApplication.eventLog.setLog("Ulogovan korisnik username: " + username + " \n");
		logService.save(MailServiceApplication.eventLog);
		
		List<User> users=userService.findAll();
		List<Account> accounts=accountService.findAll();
		ArrayList<AccountDTO> accountsDTO=new ArrayList<>();
		ArrayList<UserDTO> usersDTO=new ArrayList<>();
		for(User u: users) {
			usersDTO.add(new UserDTO(u));
		}
		for(UserDTO u: usersDTO) {
			if(u.getUserAccounts().size()>0) {
				AccountDTO a=u.getUserAccounts().get(0);
			}
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		//staro je samo bez dve gornje for petlje---------------------------
//		for(Account a : accounts) {
//			accountsDTO.add(new AccountDTO(a));
//		}
//		for (AccountDTO user : accountsDTO) {
//			System.out.println("username "+ user.getUsername());
//			System.out.println("pass "+ user.getPassword());
//			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
//				return user;
//			}
//		}
		return null;
	}
	
	@PostMapping("/update_profile")
	public User updateProfile(@RequestParam("id") int id,@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("protocol") String protocol) {
		System.err.println("update profile");
		MailServiceApplication.eventLog.setLog("Izmena profila \n");
		logService.save(MailServiceApplication.eventLog);
		System.out.println("id "+ id);
		for (User user : userService.findAll()) {
			System.out.println("user id "+ user.getId());
			if(user.getId()==id) {
				System.out.println("pass "+password);
				user.setPassword(password);
				user.setUsername(username);
				userService.save(user);
				
				return user;
			}
		}
		return null;
	}
	
	@PostMapping("/addUser")
	public UserDTO addUser(@RequestBody() UserDTO user) {
		System.err.println("register user");
		MailServiceApplication.eventLog.setLog("Registracija korisnika \n");
		logService.save(MailServiceApplication.eventLog);	
	
		User userNew= new User( user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), 
				null, null, null);
		userService.save(userNew);
		
		return user;
	}
	
	@PostMapping("/addAccount")
	public AccountDTO addAccount(@RequestBody() AccountDTO account, @RequestParam("userId") int userId) {
		System.err.println("register account");
		MailServiceApplication.eventLog.setLog("Dodavanje accounta \n");
		logService.save(MailServiceApplication.eventLog);
		
		for (User user : userService.findAll()) {
			if(user.getId()==userId) {
				System.out.println("u ifu kod dod accounta");
				ArrayList<Folder> accountFolders=new ArrayList<>();
				Folder inbox=new Folder("Inbox", null, null);
				Folder outbox=new Folder("Outbox", null, null);
				Folder drafts=new Folder("Drafts", null, null);
				accountFolders.add(inbox);
				accountFolders.add(outbox);
				accountFolders.add(drafts);
				foderService.save(inbox);
				foderService.save(outbox);
				foderService.save(drafts);
				Account acc=new Account( account.getSmpt(), 345, 25, account.getEmail(), 879, 
						account.getUsername(), account.getPassword(), account.getUsername(), accountFolders, null, user);
				accountService.save(acc);
				inbox.setFolderAccount(acc);
				outbox.setFolderAccount(acc);
				drafts.setFolderAccount(acc);
				foderService.save(inbox);
				foderService.save(outbox);
				foderService.save(drafts);
				
				accountService.save(acc);
				user.getUserAccounts().add(acc);
				userService.save(user);
			}
		
		}
		return account;
	}
}
