package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity                
@Table(name="contact")
public class Contact implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="contact_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="first_name", unique=false, nullable=false)
	private String firstName;
	
	@Column(name="last_name", unique=false, nullable=false)
	private String lastName;
	
	@Column(name="display_name", unique=false, nullable=false)
	private String displayName;
	
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	@Column(name="note", unique=false, nullable=false)
	private String note;
	
	@OneToOne
	@JoinColumn(name="user", nullable=false)
	private User user;
	
	@OneToOne
	@JoinColumn(name="photo_for_contact", nullable=true)
	private Photo photo;
	
	public Contact() {
		super();
	}
	public Contact(String firstName, String lastName, String displayName, String email, String note,
			Photo photo,User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.email = email;
		this.note = note;
		this.photo = photo;
		this.user=user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	

}
