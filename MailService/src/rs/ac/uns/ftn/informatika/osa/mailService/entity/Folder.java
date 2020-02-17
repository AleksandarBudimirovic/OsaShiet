package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.informatika.osa.mailService.dto.FolderDTO;

@Entity                
@Table(name="folder")
public class Folder implements Serializable{

	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="folder_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="folderMessage")
	private List<Message> folderMessages;
	
	@OneToOne
	@JoinColumn(name="rule_for_folder_id", nullable=true)
	private Rule destination;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="account_id",nullable=true)
	private Account folderAccount;
	
	@Column(name="word", unique=false, nullable=true)
	private String word;
	
	@OneToOne
	@JoinColumn(name="parent_folder_id", nullable=true)
	private Folder parentFolder;
	
	public Folder() {
		super();
	}
	public Folder(String name, ArrayList<Message> folderMessages, Rule destination,
			Account folderAccount, Folder parentFolder, String word) {
		super();
		this.name = name;
		this.folderMessages = folderMessages;
		this.destination = destination;
		this.folderAccount = folderAccount;
		this.parentFolder = parentFolder;
		this.word=word;
	}
	public Folder(String name,  Rule destination,
			Account folderAccount) {
		super();
		this.name = name;
		this.destination = destination;
		this.folderAccount = folderAccount;
	}
	public Folder(String name, Rule destination,
			Account folderAccount,String word) {
		super();
		this.name = name;
		this.destination = destination;
		this.folderAccount = folderAccount;
		this.word=word;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public void setFolderMessages(List<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Message> getFolderMessages() {
		return folderMessages;
	}
	@JsonIgnore
	public void setFolderMessages(ArrayList<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}
	public Rule getDestination() {
		return destination;
	}
	public void setDestination(Rule destination) {
		this.destination = destination;
	}
	public Account getFolderAccount() {
		return folderAccount;
	}
	public void setFolderAccount(Account folderAccount) {
		this.folderAccount = folderAccount;
	}
	public Folder getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}
	
	
}
