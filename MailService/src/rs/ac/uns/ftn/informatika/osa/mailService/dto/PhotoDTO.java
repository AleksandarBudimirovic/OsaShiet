package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Photo;

public class PhotoDTO implements Serializable {
	
	 	private int id;
	    private String path;
	    private ContactDTO contact;
	    private String data;
		public PhotoDTO() {
			super();
		}
		public PhotoDTO(int id, String path, ContactDTO contact,String data) {
			super();
			this.id = id;
			this.path = path;
			this.contact = contact;
			this.data=data;
		}
		public PhotoDTO(Photo photo, Contact con) {
			this(photo.getId(), photo.getPath(), new ContactDTO(con), photo.getData());
		}
		
		public PhotoDTO(Photo photo) {
			this(photo.getId(), photo.getPath(), new ContactDTO(photo.getContact()), photo.getData());
		}
		
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public ContactDTO getContact() {
			return contact;
		}
		public void setContact(ContactDTO contact) {
			this.contact = contact;
		}
		@Override
		public String toString() {
			return "PhotoDTO [id=" + id + ", path=" + path + ", contact=" + contact + "]";
		}
		
	    
	    

}
