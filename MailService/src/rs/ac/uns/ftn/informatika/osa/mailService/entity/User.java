package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                
@Table(name="user")
public class User implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="user_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="first_name", unique=false, nullable=false)
	private String firstName;
	
	@Column(name="last_name", unique=false, nullable=false)
	private String lastName;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private List<Account> userAccounts;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="userTag")
	private List<Tag> userTags;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="user")
	private List<Contact> userContacts;
	
	public User() {
		super();
	}
	public User( String username, String password, String firstName, String lastName,
			ArrayList<Account> userAccounts, ArrayList<Tag> userTags, ArrayList<Contact> userContacts) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userAccounts = userAccounts;
		this.userTags = userTags;
		this.userContacts = userContacts;
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
	public List<Account> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(ArrayList<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public List<Tag> getUserTags() {
		return userTags;
	}
	public void setUserTags(ArrayList<Tag> userTags) {
		this.userTags = userTags;
	}
	public List<Contact> getUserContacts() {
		return userContacts;
	}
	public void setUserContacts(ArrayList<Contact> userContacts) {
		this.userContacts = userContacts;
	}
	
	
}
