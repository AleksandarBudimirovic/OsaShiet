package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;

public class UserDTO implements Serializable {
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<AccountDTO> userAccounts;
	private List<ContactDTO> userContacts;
	
	public UserDTO(int id, String username, String password, String firstName, String lastName,
			List<AccountDTO> userAccounts, List<ContactDTO> userContacts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userAccounts = userAccounts;
		this.userContacts = userContacts;
	}
	public UserDTO() {
		super();
	}
	public UserDTO(User user) {
		this(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
				getAccoutnForUser(user.getUserAccounts()),getContactsForUser(user.getUserContacts()));
	}
	public static List<AccountDTO> getAccoutnForUser(List<Account> acc){
		List<AccountDTO> ret=new ArrayList<>();
		for(Account a: acc) {
			ret.add(new AccountDTO(a));
		}
		return ret;
	}
	public static List<ContactDTO> getContactsForUser(List<Contact> con){
		List<ContactDTO> ret=new ArrayList<>();
		for(Contact a: con) {
			ret.add(new ContactDTO(a));
		}
		return ret;
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
	public List<AccountDTO> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(List<AccountDTO> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public List<ContactDTO> getUserContacts() {
		return userContacts;
	}
	public void setUserContacts(List<ContactDTO> userContacts) {
		this.userContacts = userContacts;
	}
	
}
