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
@Table(name="photo")
public class Photo implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="photo_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="photo_path", unique=false, nullable=true)
	private String path;
	
	@Column(name="photo_data", unique=false, nullable=true,columnDefinition = "longtext")
	private String data;
	
	@OneToOne
	@JoinColumn(name="contact_id", nullable=true)
	private Contact contact;
	
	
	public Photo() {
		super();
	}
	public Photo(int id, String path, Contact contact, String data) {
		super();
		this.id = id;
		this.path = path;
		this.contact = contact;
		this.data=data;
	}
	public Photo( String path, String data) {
		super();
		this.path = path;
		this.data=data;
	}
	public Photo( String path) {
		super();
		this.path = path;
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
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
}
