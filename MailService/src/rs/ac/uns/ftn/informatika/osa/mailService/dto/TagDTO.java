package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;

public class TagDTO {

    private int id;
    private String name;
    private MessageDTO messages;
    
    
	public TagDTO() {
		super();
	}
	public TagDTO(int id, String name, MessageDTO messages) {
		super();
		this.id = id;
		this.name = name;
		this.messages = messages;
	}
	public TagDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TagDTO(Tag tag) {
		this(tag.getId(),tag.getName(), new MessageDTO(tag.getMessageTag()));
	}
	public TagDTO(int id, String name,Tag tag) {
		this(id,name);
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
	public MessageDTO getMessages() {
		return messages;
	}
	public void setMessages(MessageDTO messages) {
		this.messages = messages;
	}
    
    
}
