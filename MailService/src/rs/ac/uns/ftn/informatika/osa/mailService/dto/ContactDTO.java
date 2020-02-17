package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;
import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Photo;

public class ContactDTO implements Serializable{
	
	 	private int id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private PhotoDTO photo;
	    private String display;
	    private String format;
	    private ArrayList<MessageDTO> messagesTo = new ArrayList<>();
	    private ArrayList<MessageDTO> messagesFrom =  new ArrayList<>();
	    private ArrayList<MessageDTO> messagesCc = new ArrayList<>();
	    private ArrayList<MessageDTO> messagesBcc = new ArrayList<>();
	    
		public ContactDTO() {
			super();
		}

		public ContactDTO(int id, String firstName, String lastName, String email, PhotoDTO photo, String display,
				String format, ArrayList<MessageDTO> messagesTo, ArrayList<MessageDTO> messagesFrom,
				ArrayList<MessageDTO> messagesCc, ArrayList<MessageDTO> messagesBcc) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.photo = photo;
			this.display = display;
			this.format = format;
			this.messagesTo = messagesTo;
			this.messagesFrom = messagesFrom;
			this.messagesCc = messagesCc;
			this.messagesBcc = messagesBcc;
		}
		
		public ContactDTO(Contact c, PhotoDTO photo){
			this(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(),photo,c.getDisplayName(), "",new ArrayList<MessageDTO>() 
					, new ArrayList<MessageDTO>(),new ArrayList<MessageDTO>(),new ArrayList<MessageDTO>());
		}
		
		
		public ContactDTO(Contact c){
			this(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(),null,c.getDisplayName(), "",new ArrayList<MessageDTO>() 
					, new ArrayList<MessageDTO>(),new ArrayList<MessageDTO>(),new ArrayList<MessageDTO>());
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public PhotoDTO getPhoto() {
			return photo;
		}

		public void setPhoto(PhotoDTO photo) {
			this.photo = photo;
		}

		public String getDisplay() {
			return display;
		}

		public void setDisplay(String display) {
			this.display = display;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public ArrayList<MessageDTO> getMessagesTo() {
			return messagesTo;
		}

		public void setMessagesTo(ArrayList<MessageDTO> messagesTo) {
			this.messagesTo = messagesTo;
		}

		public ArrayList<MessageDTO> getMessagesFrom() {
			return messagesFrom;
		}

		public void setMessagesFrom(ArrayList<MessageDTO> messagesFrom) {
			this.messagesFrom = messagesFrom;
		}

		public ArrayList<MessageDTO> getMessagesCc() {
			return messagesCc;
		}

		public void setMessagesCc(ArrayList<MessageDTO> messagesCc) {
			this.messagesCc = messagesCc;
		}

		public ArrayList<MessageDTO> getMessagesBcc() {
			return messagesBcc;
		}

		public void setMessagesBcc(ArrayList<MessageDTO> messagesBcc) {
			this.messagesBcc = messagesBcc;
		}

		@Override
		public String toString() {
			return "ContactDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", photo=" + photo + ", display=" + display + ", format=" + format + ", messagesTo=" + messagesTo
					+ ", messagesFrom=" + messagesFrom + ", messagesCc=" + messagesCc + ", messagesBcc=" + messagesBcc
					+ "]";
		}

		
	    
}
