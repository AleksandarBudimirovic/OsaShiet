package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.osa.mailService.dto.AttachmentDTO;

@Entity                
@Table(name="attachment")
public class Attachment implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="attachment_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="mime_type", unique=false, nullable=false)
	private String mimeType;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Column(name="data", unique=false, nullable=false,columnDefinition = "longtext")
	private String data;
	
	@ManyToOne
	@JoinColumn(name="message_attachment", nullable=true)
	private Message messageAttachment;
	
	public Attachment() {
		super();
	}
	public Attachment( String mimeType, String name, String data, Message messageAttachment) {
		super();
		this.mimeType = mimeType;
		this.name = name;
		this.data = data;
		this.messageAttachment = messageAttachment;
	}
	public Attachment( String mimeType, String name, String data) {
		super();
		this.mimeType = mimeType;
		this.name = name;
		this.data = data;
	}
	public static ArrayList<Attachment> getAttachments(ArrayList<AttachmentDTO> att){
		ArrayList<Attachment> ret=new ArrayList<>();
		for(AttachmentDTO a : att) {
			ret.add(new Attachment(a.getType(), a.getName(), a.getData()));
		}
		return ret;
	}
	public Message getMessageAttachment() {
		return messageAttachment;
	}
	public void setMessageAttachment(Message messageAttachment) {
		this.messageAttachment = messageAttachment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
