package rs.ac.uns.ftn.informatika.osa.mailService.controller;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.*;
import rs.ac.uns.ftn.informatika.osa.mailService.service.AccountServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.FolderServiceInterface;
import rs.ac.uns.ftn.informatika.osa.mailService.service.MessageServiceInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.osa.mailService.dto.*;

public class MessagesToFolders {

	public static List<Account> accounts=new ArrayList<>();
	public static List<Message> messages=new ArrayList<>();
	
	public MessagesToFolders(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	public static List<Message> getAllMessagesForUser( int id){
		List<Message> ret = new ArrayList<Message>();
		Account loggedInUser=null;
		for (Account user : accounts) {
			if(user.getId() == id) {
				loggedInUser=user;
//				ret =  user.getAccountMessages();
			}
		}
		for(Message m: messages) {
			if(m.getAccount()!=null) {
				if(m.getAccount().getId()==loggedInUser.getId())
					ret.add(m);
			}
		}
//		ret.addAll(Mail.msd)
		return ret;
	}
	
	public static List<Folder> getAllFoldersForUser( int userId) {
		List<Folder> folders = new ArrayList<Folder>();
		
		for (Account a : accounts) {
			if(a.getId() == userId) {
				folders = a.getAccountFolders();
			}
		}
		return folders;
	}
	
	public static ArrayList<Message> sortMessages(Folder folder, List<Message> messages) {
		System.out.println("jesmo uopste tu");
		ArrayList<Message> messForFolder=new ArrayList<>();		
        String word=folder.getWord();
        Rule ruleForFolder=folder.getDestination();
        ArrayList<Message> messRemove=new ArrayList<>();
        for(Message n: messages ){
            if(ruleForFolder.getCondition()== Condition.TO){
                if(n.getTo().toLowerCase().contains(word.toLowerCase()))
                    if(ruleForFolder.getOperation()== Operation.COPY) {
                    	messForFolder.add(n);
                    	n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
//                    	n.setFolderMessage(folder);
                    	
                    }
                    else if(ruleForFolder.getOperation()== Operation.MOVE){
                    	messForFolder.add(n);
                        messRemove.add(n);
                        n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
//                        n.setFolderMessage(folder);
//                        if(n.getFolderMessage()==null) {
//                    		n.setFolderMessage(folder);
//                    	}
                    }else
                        messRemove.add(n);
            }
            else if(ruleForFolder.getCondition()== Condition.CC){
                if(word != null){
                    if(n.getCc().toLowerCase().contains(word.toLowerCase()))
                        if(ruleForFolder.getOperation()== Operation.COPY) {
                        	messForFolder.add(n);
                        	n.setFolderMessage(folder);
                        	folder.getFolderMessages().add(n);
//                        	n.setFolderMessage(folder);
//                        	if(n.getFolderMessage()==null) {
//                        		n.setFolderMessage(folder);
//                        	}
                        }
                        else if(ruleForFolder.getOperation()== Operation.MOVE){
                        	messForFolder.add(n);
                            messRemove.add(n);
                            n.setFolderMessage(folder);
                        	folder.getFolderMessages().add(n);
//                            n.setFolderMessage(folder);
//                            if(n.getFolderMessage()==null) {
//                        		n.setFolderMessage(folder);
//                        	}
                        }else
                            messRemove.add(n);
                }
            }
            else if(ruleForFolder.getCondition()== Condition.FROM){
                if(n.getFrom().toLowerCase().contains(word.toLowerCase()))
                    if(ruleForFolder.getOperation()== Operation.COPY) {
                    	messForFolder.add(n);
                    	n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
//                    	n.setFolderMessage(folder);
//                    	if(n.getFolderMessage()==null) {
//                    		n.setFolderMessage(folder);
//                    	}
                    }
                    else if(ruleForFolder.getOperation()== Operation.MOVE){
                    	messForFolder.add(n);
                        messRemove.add(n);
                        n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
//                        n.setFolderMessage(folder);
//                        if(n.getFolderMessage()==null) {
//                    		n.setFolderMessage(folder);
//                    	}
                    }else
                        messRemove.add(n);
            }
            else if(ruleForFolder.getCondition()== Condition.SUBJECT){
                if(n.getSubject().toLowerCase().contains(word.toLowerCase())) {
                    if(ruleForFolder.getOperation()== Operation.COPY) {
                    	messForFolder.add(n);
                    	n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
                    }
                    else if(ruleForFolder.getOperation()== Operation.MOVE){
                    	messForFolder.add(n);
                    	n.setFolderMessage(folder);
                    	folder.getFolderMessages().add(n);
                        messRemove.add(n);
                    }else
                        messRemove.add(n);
                }
            }
        }
//                EmailsActivity.messages.removeAll(messRemove);
//                mess.removeAll(messRemove);
        folder.setFolderMessages(messForFolder);
        return messRemove;
	}
	
	public static void messagesInbox(Folder folder, List<Message> messages,String loggedInUserEmail) {
		ArrayList<Message> messForFolder=new ArrayList<>();
	
        if(messages.size()!=0) {
            for (Message m : messages) {
                if(m.getTo() != null){
                    if ((m.getTo().contains(loggedInUserEmail) )) {
                    	messForFolder.add(m);
                    	System.out.println("INBOX "+ m.getSubject());
                    	if(m.getFolderMessage()==null) {
                    		m.setFolderMessage(folder);
                    	}
                    }
                }
                if(m.getBcc() != null){
                    if ( m.getBcc().contains(loggedInUserEmail)) {
                    	messForFolder.add(m);
                    	if(m.getFolderMessage()==null) {
                    		m.setFolderMessage(folder);
                    	}
                    }
                }
                if(m.getCc() != null){
                    if (  m.getCc().contains(loggedInUserEmail)) {
                    	messForFolder.add(m);
                    	if(m.getFolderMessage()==null) {
                    		m.setFolderMessage(folder);
                    	}
                    }
                }
            }
        }
        folder.setFolderMessages(messForFolder);
	}
	
	public static void messagesOutbox(Folder folder, List<Message> messages,String loggedInUserEmail) {
		ArrayList<Message> messForFolder=new ArrayList<>();
		
        if(messages.size()!=0) {
            for (Message m : messages) {
                String oneContact=m.getFrom();
                if(oneContact.contains(":")) {
                    oneContact=oneContact.split(":")[1];
                    if(m.getFolderMessage()==null) {
                		m.setFolderMessage(folder);
                	}
                }
                if (  oneContact.equals(loggedInUserEmail) ) {
                	System.out.println("outbox SADRZI");
                	messForFolder.add(m);
                	if(m.getFolderMessage()==null) {
                		m.setFolderMessage(folder);
                	}
                }
            }
        }
        folder.setFolderMessages(messForFolder);
	}
}
