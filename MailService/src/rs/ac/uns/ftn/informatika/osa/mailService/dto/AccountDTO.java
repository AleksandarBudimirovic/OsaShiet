package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;

public class AccountDTO implements Serializable{
	
	 private int id;
	    private String username;
	    private String password;
	    private String smpt;
	    private String email;
	    private String pop3imap;
	    private ArrayList<MessageDTO> messages;
	    private ArrayList<ContactDTO> contacts = new ArrayList<ContactDTO>();
	    private boolean logedIn;
	    
		public AccountDTO() {
			super();
		}

		public AccountDTO(int id, String email,String username, String password, String smpt, String pop3imap,
				ArrayList<MessageDTO> messages, boolean logedIn,ArrayList<ContactDTO> contacts) {
			super();
			this.id = id;
			this.email=email;
			this.username = username;
			this.password = password;
			this.smpt = smpt;
			this.pop3imap = pop3imap;
			this.messages = messages;
			this.logedIn = logedIn;
			this.contacts=contacts;
		}
		
		public AccountDTO(Account a){
			this(a.getId(),a.getInServerAddress(), a.getUsername(), a.getPassword(), Integer.toString(a.getSmtpPort()), "", getMessages(a.getAccountMessages()), false,getContacts(a.getUser().getUserContacts()));
		}
		
		public static ArrayList<MessageDTO> getMessages(List<Message> mess) {
			ArrayList<MessageDTO> messages=new ArrayList<>();
			for(Message m : mess) {
				messages.add(new MessageDTO(m));
			}
			return messages;
		}
		
		public static ArrayList<ContactDTO> getContacts(List<Contact> con) {
			ArrayList<ContactDTO> contacts=new ArrayList<>();
			for(Contact c : con) {
				contacts.add(new ContactDTO(c));
			}
			return contacts;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public ArrayList<ContactDTO> getContacts() {
			return contacts;
		}

		public void setContacts(ArrayList<ContactDTO> contacts) {
			this.contacts = contacts;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSmpt() {
			return smpt;
		}

		public void setSmpt(String smpt) {
			this.smpt = smpt;
		}

		public String getPop3imap() {
			return pop3imap;
		}

		public void setPop3imap(String pop3imap) {
			this.pop3imap = pop3imap;
		}

		public ArrayList<MessageDTO> getMessages() {
			return messages;
		}

		public void setMessages(ArrayList<MessageDTO> messages) {
			this.messages = messages;
		}

		public boolean isLogedIn() {
			return logedIn;
		}

		public void setLogedIn(boolean logedIn) {
			this.logedIn = logedIn;
		}

		@Override
		public String toString() {
			return "AccountDTO [id=" + id + ", username=" + username + ", password=" + password + ", smpt=" + smpt
					+ ", pop3imap=" + pop3imap + ", messages=" + messages + ", logedIn=" + logedIn + "]";
		}
		
		

}
