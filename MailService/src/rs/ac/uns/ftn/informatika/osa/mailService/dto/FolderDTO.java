package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Rule;

public class FolderDTO implements Serializable {
	
	 private int id;
	 private String name;
	 private Rule rule;
	 @JsonIgnore
	 private ArrayList<FolderDTO> childFolders = new ArrayList<>();
	 private FolderDTO parentFolder;
	 private ArrayList<MessageDTO> messages;
	 private String word;
	 	 
	public FolderDTO() {
		super();
	}

	public FolderDTO(int id, String name, ArrayList<FolderDTO> childFolders, FolderDTO parentFolder,
			ArrayList<MessageDTO> messages,String word,Rule rule) {
		super();
		this.id = id;
		this.name = name;
		this.childFolders = childFolders;
		this.parentFolder = parentFolder;
		this.messages = messages;
		this.word=word;
		this.rule=rule;
	}
	
	public FolderDTO(Folder folder) {
		this(folder.getId(), folder.getName(), null, null, AccountDTO.getMessages(folder.getFolderMessages()), folder.getWord(), folder.getDestination());
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
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

	public ArrayList<FolderDTO> getChildFolders() {
		return childFolders;
	}

	public void setChildFolders(ArrayList<FolderDTO> childFolders) {
		this.childFolders = childFolders;
	}

	public FolderDTO getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(FolderDTO parentFolder) {
		this.parentFolder = parentFolder;
	}

	public ArrayList<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<MessageDTO> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "FolderDTO [id=" + id + ", name=" + name + ", childFolders=" + childFolders + ", parentFolder="
				+ parentFolder + ", messages=" + messages + "]";
	}

	
}
