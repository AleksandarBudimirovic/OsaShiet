package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity                
@Table(name="account")
public class Account implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="account_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="smtp_address", unique=false, nullable=false)
	private String smtpAddress;
	
	@Column(name="smtp_port", unique=false, nullable=false)
	private int smtpPort;
	
	@Column(name="in_server_type", unique=false, nullable=false)
	private int inServerType;
	
	@Column(name="in_server_address", unique=false, nullable=false)
	private String inServerAddress;
	
	@Column(name="in_server_port", unique=false, nullable=false)
	private int inServerPort;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="display_name", unique=false, nullable=false)
	private String displayName;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="folderAccount")
	private List<Folder> accountFolders;
	
	//ovaj jsonignore je mozda prolematican
	@JsonIgnore
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="account")
	private List<Message> accountMessages;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="user_id", nullable=true)
	private User user;
	
	public Account() {
		super();
	}
	public Account(int id, String smtpAddress, int smtpPort, int inServerType, String inServerAddress, int inServerPort,
			String username, String password, String displayName, ArrayList<Folder> accountFolders,
			ArrayList<Message> accountMessages, User user) {
		super();
		this.id = id;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtpPort;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.accountFolders = accountFolders;
		this.accountMessages = accountMessages;
		this.user = user;
	}
	public Account( String smtpAddress, int smtpPort, int inServerType, String inServerAddress, int inServerPort,
			String username, String password, String displayName, ArrayList<Folder> accountFolders,
			ArrayList<Message> accountMessages, User user) {
		super();
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtpPort;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.accountFolders = accountFolders;
		this.accountMessages = accountMessages;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSmtpAddress() {
		return smtpAddress;
	}
	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public int getInServerType() {
		return inServerType;
	}
	public void setInServerType(int inServerType) {
		this.inServerType = inServerType;
	}
	public String getInServerAddress() {
		return inServerAddress;
	}
	public void setInServerAddress(String inServerAddress) {
		this.inServerAddress = inServerAddress;
	}
	public int getInServerPort() {
		return inServerPort;
	}
	public void setInServerPort(int inServerPort) {
		this.inServerPort = inServerPort;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Folder> getAccountFolders() {
		return accountFolders;
	}
	public void setAccountFolders(ArrayList<Folder> accountFolders) {
		this.accountFolders = accountFolders;
	}
	public List<Message> getAccountMessages() {
		return accountMessages;
	}
	public void setAccountMessages(ArrayList<Message> accountMessages) {
		this.accountMessages = accountMessages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
