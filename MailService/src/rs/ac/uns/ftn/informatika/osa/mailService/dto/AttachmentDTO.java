package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;

public class AttachmentDTO implements Serializable {
	
	private int id;
	private String data;
	private String type;
	private String name;
	private MessageDTO message;
	
	public AttachmentDTO() {
		super();
	}

	public AttachmentDTO(int id, String data, String type, String name, MessageDTO message) {
		super();
		this.id = id;
		this.data = data;
		this.type = type;
		this.name = name;
		this.message = message;
	}
	
	public AttachmentDTO(int id, String data, String type, String name) {
		super();
		this.id = id;
		this.data = data;
		this.type = type;
		this.name = name;
	}
	
	//message treba da bude dto ali da bi se poruka napravila treba mi attachmentdto??
	public AttachmentDTO(Attachment a, Message m){
		this(a.getId(), a.getData(), a.getMimeType(), a.getName(), new MessageDTO(m));
	}
	
	public AttachmentDTO(Attachment a){
		this(a.getId(), a.getData(), a.getMimeType(), a.getName());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MessageDTO getMessage() {
		return message;
	}

	public void setMessage(MessageDTO message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AttachmentDTO [id=" + id + ", data=" + data + ", type=" + type + ", name=" + name + ", message="
				+ message + "]";
	}
	

	
}
