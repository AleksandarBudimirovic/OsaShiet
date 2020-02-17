package rs.ac.uns.ftn.informatika.osa.mailService.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.mailService.MailServiceApplication;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.AccountDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.AttachmentDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.MessageDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;
import rs.ac.uns.ftn.informatika.osa.mailService.mailUtils.Mail;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AccountServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AttachmentServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.ContactServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.EvenLogServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.FolderServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.MessageServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.TagServiceInterface;

@Transactional
@RestController
@RequestMapping(value="/messages")
public class MessageController {
	
	@Autowired
	private AccountServiceInterface accountService;
	@Autowired
	private MessageServiceInterface messageService;
	@Autowired
	private FolderServiceInterface folderService;
	@Autowired
	private TagServiceInterface tagService;
	@Autowired
	private EvenLogServiceInterface logService;
	@Autowired
	private AttachmentServiceInterface attService;
	
	public static List<Account> accounts=new ArrayList<>();
	public static List<Message> messages=new ArrayList<>();
	public static Account loggedIn=null;
	
	@RequestMapping("/getAll")
	public ArrayList<MessageDTO> getAllMessages(@RequestParam("userId") int id){
		
		Mail mailAPI = new Mail();
		loggedIn=accountService.findOne(2);
		accounts=accountService.findAll();
		Folder inbocFolder=null;
		
		ArrayList<MessageDTO> ret = new ArrayList<MessageDTO>();
		List<Message> mess = new ArrayList<Message>();
//
//		List<Message> messTest = messageService.findMessagesForAccount(1);
//		
//		ArrayList<Message> messagesToSaveForAccount= new ArrayList<>();
		List<Message> existingMessages= messageService.findAll();
//		List<Message> messages= messageService.findMessagesForAccount(2);
		ArrayList<Message> fetchResult=mailAPI.fetch( loggedIn ,false, existingMessages);
		
		for(Message m : fetchResult) {
			if(messageService.findMessageByGmailId(m.getGmailId()) == null) {
				List<Attachment> atts= m.getMessageAttachments();
				if(atts != null) {
					if(atts.size()>0) {
						m.setMessageAttachments(null);
						Message messWithId=messageService.save(m);
						for(Attachment att : atts) {
							att.setMessageAttachment(messWithId);
							attService.save(att);
						}
						m.setMessageAttachments(atts);
						messageService.save(m);
					}
				}
				System.out.println("save mess");
				messageService.save(m);
			}
		}
		
		List<Message> messagesFromDB=messageService.findAll();
		for(Message m : messagesFromDB) {
			if(m.getMessageAttachments() != null) {
				if(m.getMessageAttachments().size()>0) {
					ret.add(new MessageDTO(m, m.getMessageAttachments()));
				}
			}else {
				ret.add(new MessageDTO(m));
			}
		}
		
		return ret;
	}
	
	public static void testiranje(List<Folder> folders) {
		for(Folder f: folders) {
			for(Message m : f.getFolderMessages())
				System.out.println("from "+m.getFrom()+ "to "+ m.getTo());
		}
	}
	
//	public static void messagesToFoldersNewFolder(int id, String loggedInUserEmail) {
//		MessagesToFolders.accounts=accounts;
//		MessagesToFolders.messages=messages;
//		System.out.println("poruke "+messages);
//		if(messages.size()>0) {
//			for(Message m : messages)
//				System.out.println("test poruka za usera "+ m.getContent());
//			List<Message> messForUser=MessagesToFolders.getAllMessagesForUser(id);
//			List<Folder> foldersForUser=MessagesToFolders.getAllFoldersForUser(id);
//			ArrayList<Message> messRemove=new ArrayList<>();
//			for(Folder f : foldersForUser) {
//				System.out.println("FOLDER NAME "+f.getName());
//				if(!f.getName().equals("Inbox") && !f.getName().equals("Outbox") && !f.getName().equals("Drafts")) {
//					System.out.println("pronadjen folder");
//					messRemove=MessagesToFolders.sortMessages( f, messForUser);
//				}
//			}
//			messForUser.removeAll(messRemove);
//			for(Folder f : foldersForUser) {
//				if(f.getName().equals("Inbox"))
//					MessagesToFolders.messagesInbox(f, messForUser, loggedInUserEmail);
//				else if(f.getName().equals("Outbox"))
//					MessagesToFolders.messagesOutbox(f, messForUser, loggedInUserEmail);
//			}
//		}
//	}
	
	public static void messagesToFolders(int id, String loggedInUserEmail) {
		MessagesToFolders.accounts=accounts;
		MessagesToFolders.messages=messages;
		List<Message> messForUser=MessagesToFolders.getAllMessagesForUser(id);
		List<Folder> foldersForUser=MessagesToFolders.getAllFoldersForUser(id);
		if(messages.size()>0) {
			for(Folder f : foldersForUser) {
				if(f.getName().equals("Inbox"))
					MessagesToFolders.messagesInbox(f, messForUser, loggedInUserEmail);
				else if(f.getName().equals("Outbox"))
					MessagesToFolders.messagesOutbox(f, messForUser, loggedInUserEmail);
			}
		}
	}
	
	@PostMapping("/addMessage")
	public MessageDTO createMessage(@RequestBody() MessageDTO message, @RequestParam("userId") int userId) {
		MailServiceApplication.eventLog.setLog("Dodavanje poruke \n");
		logService.save(MailServiceApplication.eventLog);
		
		accounts=accountService.findAll();
		messages=messageService.findAll();
		
		
		Message newMessage;
		Folder folderOutbox=null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				for(Folder f:a.getAccountFolders()) {
					if(f.getName().equals("Outbox"))
						folderOutbox=f;
				}
			}
		}
		System.out.println("add message--------------------------");
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				message.setDraft(false);
				System.out.println(message.getDateTime());
//				message.setId(hashCode());
				Folder f=null;
				if( message.getFolder()!=null) {
					f=new Folder( message.getFolder().getName(), null, a);
					folderService.save(f);
				}
				if(message.getAttachments().size()>0) {
					newMessage=new Message( message.getFrom(), message.getTo(), message.getCc(),
							message.getBcc(), message.getDateTime(), message.getSubject(), message.getContent(),
							false, f,false,Attachment.getAttachments(message.getAttachments()));
				}else {
					newMessage=new Message( message.getFrom(), message.getTo(), message.getCc(),
							message.getBcc(), message.getDateTime(), message.getSubject(), message.getContent(),
							false, f, null, false);
				}
				newMessage.setAccount(a);
				newMessage.setFrom(a.getInServerAddress());
				a.getAccountMessages().add(newMessage);
				newMessage.setFolderMessage(a.getAccountFolders().get(2));
				messageService.save(newMessage);
				accountService.save(a);
//			API-------------------------------------------------------------------------------------
				boolean state = Mail.sendMail(newMessage, a);
				System.out.println(state);
				
				if(state == true) {
					folderOutbox.getFolderMessages().add(newMessage);
					folderService.save(folderOutbox);
					accountService.save(a);
				}
				
			}
		}
		return message;
	}
	
	public static List<Attachment> getAttachmentsFromDTO(ArrayList<AttachmentDTO> attachments, Message m){
		List<Attachment> ret=new ArrayList<>();
		for(AttachmentDTO a : attachments) {
			Attachment att=new Attachment( a.getType(), a.getName(), a.getData(),m);
			ret.add(att);
		}
		return ret;
	}
	
	@DeleteMapping("/delete")
	public ArrayList<MessageDTO> deleteMessage(@RequestParam("messageId") int messageId, @RequestParam("userId") int userId) {
		System.out.println("delete message");
		MailServiceApplication.eventLog.setLog("Brisanje poruke \n");
		logService.save(MailServiceApplication.eventLog);
		
		Account acc = null;
		ArrayList<MessageDTO> ret = new ArrayList<MessageDTO>();
		List<Message> mess = new ArrayList<Message>();
		for(Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				acc = a;
				Message m = null;
				for (Message c : a.getAccountMessages()) {
					if(c.getId() == messageId) {
						m = c;
					}
					mess.add(c);
				}
				if(m != null) {
					a.getAccountMessages().remove(m);
				}
				messageService.remove(m.getId());
				accountService.save(a);
			}
		}
		for(Message m : mess) {
			ret.add(new MessageDTO(m));
		}
		return ret;
	}
	
	@PostMapping("/readMessage")
	public ArrayList<MessageDTO> readMessage(@RequestParam("userId") int userId, @RequestParam("messageId") int messageId){
		System.out.println("read mess");
		Account user = null;
		ArrayList<MessageDTO> ret = new ArrayList<MessageDTO>();
		for (Account u : accountService.findAll()) {
			if(u.getId() == userId) {
				user = u;
			}
		}
		if(user != null) {
			for (Message m : messageService.findAll()) {
				ret.add(new MessageDTO(m));
				if(m.getId()==messageId) {
					m.setUnread(false);
					messageService.save(m);
				}
			}
		}
		
		return ret;
	}
	
	@PostMapping("/savetodraft")
	public MessageDTO saveToDraft(@RequestBody() MessageDTO message, @RequestParam("userId") int userId) {
		System.out.println("save to draft");
		MailServiceApplication.eventLog.setLog("Poruka sacuvana u drafts folder \n");
		logService.save(MailServiceApplication.eventLog);
		
		Folder folderDrafts=null;
		Account acc = null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				acc = a;
			}
		}
		for(Folder f: acc.getAccountFolders()) {
			if(f.getName().equals("Drafts"))
				folderDrafts=f;
		}
		Message newMessage=new Message( message.getFrom(), message.getTo(), message.getCc(),
				message.getBcc(), message.getDateTime(), message.getSubject(), message.getContent(),
				false, acc.getAccountFolders().get(0), null, false);
		newMessage.setAccount(acc);
		newMessage.setFrom(acc.getInServerAddress());
		acc.getAccountMessages().add(newMessage);
		
		if(message.getId() == 0) {
			message.setDraft(true);
			newMessage.setDraft(true);
			folderDrafts.getFolderMessages().add(newMessage);
			
		}else {
			for(Message m : folderDrafts.getFolderMessages()) {
				if(m.getId() == message.getId()) {
					m.setBcc(message.getBcc());
//					return message;
				}
			}
		}
		messageService.save(newMessage);
		accountService.save(acc);
		
		return message;
	}
	
	@DeleteMapping("/deleteDraft")
	public ArrayList<MessageDTO> deleteDraft(@RequestParam("messageId") int messageId, @RequestParam("userId") int id){
		System.out.println("delete draft");
		MailServiceApplication.eventLog.setLog("Brisanje poruke iz drafts foldera \n");
		logService.save(MailServiceApplication.eventLog);
		
		Folder folderDrafts=null;
		Account acc = null;
		Message forDelete = null;
		for(Account a : accountService.findAll()) {
			if(a.getId() == id) {
				acc = a;
			}
		}
		for(Folder f: acc.getAccountFolders()) {
			if(f.getName().equals("Drafts"))
				folderDrafts=f;
		}
		if(acc != null) {
			for(Message m : folderDrafts.getFolderMessages()) {
				if(m.getId() == messageId) {
					forDelete = m;
				}
			}
		}
		if(forDelete != null) {
			folderDrafts.getFolderMessages().remove(forDelete);
			messageService.remove(messageId);
			accountService.save(acc);
		}
		return new ArrayList<MessageDTO>();
	}
	
	@PutMapping("/tags")
	public ArrayList<MessageDTO> updateMessageTag(@RequestParam("id") int id, @RequestParam("userId") int userId,@RequestBody() Tag tag ){
		Account user = null;
		System.out.println("tags");
//		MailServiceApplication.eventLog.setLog("Dodati tag \n");
//		logService.save(MailServiceApplication.eventLog);
		
		for(Account a : accountService.findAll()) {
			if(a.getId() ==  userId) {
				user = a;
			}
		}
		Tag tagNew=new Tag( tag.getName(), tag.getMessageTag(), user.getUser());
		
		if(user != null) {
			for(Message m : user.getAccountMessages()) {
				if(m.getId() == id) {
					ArrayList<Tag> tags=new ArrayList<>();
					tags.add(tagNew);
					m.setMessageTag(tags);
					tagNew.setMessageTag(m);
					tagService.save(tagNew);
					messageService.save(m);
					System.out.println("TAG "+ tag.getName());
				}
			}
		}
		ArrayList<MessageDTO> ret=new ArrayList<>();
		for(Message m : user.getAccountMessages())
			ret.add(new MessageDTO(m));
		
		return ret;
	}
	
	@PostMapping("/searchMessages")
	public ArrayList<MessageDTO> searchMessages(@RequestParam("keyword") String keyword, @RequestParam("userId") int userId){
		System.out.println("serach mess");
		ArrayList<Message> filteredMessages = new ArrayList<Message>();
		Account user = null;
		ArrayList<MessageDTO> ret=new ArrayList<>();

		for(Account u : accountService.findAll()) {
			if(u.getId() == userId) {
				user = u;
			}
		}
		
		if(user != null) {
			if(keyword.equals("")) {
				filteredMessages.addAll(user.getAccountMessages());
			}else {
				for(Message message : user.getAccountMessages()) {
					if(!message.isDraft()) {
						if(message.getSubject().toLowerCase().startsWith(keyword.toLowerCase())) {
							filteredMessages.add(message);
						}
						if(message.getFrom().toLowerCase().startsWith(keyword.toLowerCase())) {
							filteredMessages.add(message);
						}
						if(message.getContent().toLowerCase().startsWith(keyword.toLowerCase())) {
							filteredMessages.add(message);
						}
						if(message.getTo().toLowerCase().startsWith(keyword.toLowerCase())){
							filteredMessages.add(message);
						}
						if(message.getCc() != null) {
							if(message.getCc().toLowerCase().startsWith(keyword.toLowerCase())) {
								filteredMessages.add(message);
							}
						}
						if(message.getBcc() != null) {
							if(message.getBcc().toLowerCase().startsWith(keyword.toLowerCase())) {
								filteredMessages.add(message);
							}
						}
					}
				}
			}
		}
		for(Message m : filteredMessages)
			ret.add(new MessageDTO(m));
		
		return ret;
	}

}
