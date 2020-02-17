package rs.ac.uns.ftn.informatika.osa.mailService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.mailService.MailServiceApplication;
import rs.ac.uns.ftn.informatika.osa.mailService.dto.FolderDTO;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Rule;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Condition;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Operation;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AccountServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.EvenLogServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.FolderServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.MessageServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.RuleServiceInterface;

@RestController
@RequestMapping(value="/folders")
public class FolderController {

	@Autowired
	private FolderServiceInterface folderService;
	@Autowired
	private AccountServiceInterface accountService;
	@Autowired
	private RuleServiceInterface ruleService;
	@Autowired
	private EvenLogServiceInterface logService;
	@Autowired
	private MessageServiceInterface messageService;
	
	@RequestMapping("/getAll")
	public ArrayList<FolderDTO> getAllFolders(@RequestParam("userId") int userId) {
		ArrayList<FolderDTO> foldersDTO=new ArrayList<>();
		List<Folder> folders = folderService.findAll();
		Account loggedIn=null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				loggedIn=a;
				folders =  a.getAccountFolders();
			}
		}
		ArrayList<FolderDTO> childFolders=new ArrayList<>();
		for(Folder f: folders) {
			if(f.getParentFolder()==null) {
				FolderDTO folderDTo=new FolderDTO(f);
		
				foldersDTO.add(folderDTo);
			}else if(f.getParentFolder()!=null) {
				FolderDTO childFolderDTo=new FolderDTO(f);
				childFolders.add(childFolderDTo);
			}
		}
//		for(FolderDTO f :  childFolders) {
//			for(FolderDTO fo : foldersDTO) {
//				if(f.getParentFolder()!=null) {
//					if(f.getParentFolder().getId()==fo.getId()) {
//						ArrayList<FolderDTO> childFoldersArray=new ArrayList<>();
//						childFoldersArray.add(f);
//						fo.setChildFolders(childFoldersArray);
//					}
//				}	
//			}
////			foldersDTO.
////			int idOfParent=foldersDTO.indexOf(f.getParentFolder());
////			ArrayList<FolderDTO> childFoldersArray=new ArrayList<>();
////			childFoldersArray.add(f);
////			foldersDTO.get(idOfParent).setChildFolders(childFoldersArray);
//		}
		for(FolderDTO f :  foldersDTO) {
			System.out.println("FOLDERI");
			System.out.println("PORUKE "+ f.getMessages().size());
		}
//		messagesToFolders(userId, loggedIn.getEmail());
		return foldersDTO;
	}
	
//	public void setChildFolders(ArrayList<FolderDTO> folders) {
//		for(FolderDTO f: folders) {
//			if(f.getParentFolder()!=null) {
//				f.getParentFolder()
//			}
//				
//		}
//	}
	
	@DeleteMapping("/delete")
	public ArrayList<FolderDTO> deleteFolder(@RequestParam("folderId") int id, @RequestParam("userId") int userId) {
		System.out.println("brisanje foldera user id  " + userId);
		MailServiceApplication.eventLog.setLog("Brisanje foldera \n");
		logService.save(MailServiceApplication.eventLog);
		
		List<Folder> folders = new ArrayList<Folder>();
		ArrayList<FolderDTO> foldersDTO=new ArrayList<>();
		Account acc = null;
		Folder ff = null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				acc = a;
				folders = a.getAccountFolders();
			}
		}
		if(acc != null) {
			for (Folder folder : folders) {
				if(folder.getId() == id) {
					System.out.println("pre remove");
					ff  = folder;
				}
			}
		}	
		
		if(ff != null) {
			acc.getAccountFolders().remove(ff);
			accountService.save(acc);
			folderService.remove(ff.getId());
		}
		
//		FILTRIRANJE PORUKA-----------------------------------------------------------------
		MessageController.messagesToFolders(id, acc.getInServerAddress());
		for(Folder f: folders) {
			foldersDTO.add(new FolderDTO(f));
		}
		
		return foldersDTO;
	}
	
	@PostMapping("/addFolder")
	public FolderDTO createFolder(@RequestBody() FolderDTO folder, @RequestParam("userId") int userId) {
		System.out.println("create folder");
		MailServiceApplication.eventLog.setLog("Dodavanje foldera \n");
		logService.save(MailServiceApplication.eventLog);
		MessageController.messages=messageService.findAll();
		Folder folderNewTemp=null;
		
		Account loggedIn=null;
		for (Account a : accountService.findAll()) {
			if(a.getId() == userId) {
				loggedIn=a;
				System.out.println("novi folder------: "+folder.getRule().getCondition()+" op "+folder.getRule().getOperation());
				Rule rule=new Rule( folder.getRule().getCondition(), folder.getRule().getOperation(), null);
				ruleService.save(rule);	
				
				folderNewTemp=new Folder(folder.getName(), rule, a,folder.getWord());
				folderNewTemp.setFolderMessages(new ArrayList<>());
				a.getAccountFolders().add(folderNewTemp);
				folderService.save(folderNewTemp);
				if(folder.getParentFolder()!=null) {
					System.out.println("parentfolder "+ folder.getParentFolder().getName());
					Rule ruleParent=null;
					if(folder.getParentFolder().getRule()!=null) {
						ruleParent=new Rule( folder.getParentFolder().getRule().getCondition(), folder.getParentFolder().getRule().getOperation(), null);
						ruleService.save(rule);}
					Folder folderNewParent=folderService.findOne(folder.getParentFolder().getId());
					folderNewTemp.setParentFolder(folderNewParent);
					folderService.save(folderNewTemp);
				}
//				folderNewTemp.setDestination(rule);
//				ruleService.save(rule);
				
//				ruleService.save(rule);	
				accountService.save(a);
			}
		}
//		FILTRIRANJE PORUKA U FOLDERE---------------------------------------------------------
		ArrayList<Message> messToRemoveFromFolders=MessagesToFolders.sortMessages(folderNewTemp, MessageController.messages);
		for(Folder f: loggedIn.getAccountFolders()) {
			for(Message m : messToRemoveFromFolders) {
				if(f.getFolderMessages().contains(m))
					f.getFolderMessages().remove(m);
			}
		}
//		folderService.save(folderNewTemp);
//		accountService.save(loggedIn);
//		MessageController.messagesToFoldersNewFolder(userId, loggedIn.getSmtpAddress());
		System.out.println("folder new mess after filtering "+ folderNewTemp.getFolderMessages().size());
		folderService.save(folderNewTemp);
		return folder;
	}
	
	@PutMapping
	public ArrayList<FolderDTO> updateFolder(@RequestParam("id") int id, @RequestParam("folderName") String name, @RequestParam("folderOperation") String operation, @RequestParam("folderCondition") String condition, @RequestParam("userId") int userId){
		System.out.println("update folder");
		MailServiceApplication.eventLog.setLog("Izmena foldera \n");
		logService.save(MailServiceApplication.eventLog);
	
		ArrayList<FolderDTO> foldersDTO=new ArrayList<>();
		Account user = null;
		
		System.out.println("uid. " + userId);
		System.out.println("fid: " + id);
		
		for(Account a : accountService.findAll()) {
			if(a.getId() ==  userId) {
				user = a;
			}
		}
		
		if(user != null) {
			for(Folder f : user.getAccountFolders()) {
				if(f.getId() == id) {
					f.setName(name);
					f.getDestination().setCondition(Condition.valueOf(condition));
					f.getDestination().setOperation(Operation.valueOf(operation));
					folderService.save(f);
				}
			}
		}
		accountService.save(user);
		for(Folder f: user.getAccountFolders()) {
			foldersDTO.add(new FolderDTO(f));
		}
		
		return foldersDTO;
	}
}
