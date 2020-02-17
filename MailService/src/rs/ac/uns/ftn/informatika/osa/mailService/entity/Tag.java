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
@Table(name="tag")
public class Tag implements Serializable{

	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="tag_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="tag_name", unique=false, nullable=false)
	private String name;
	
	@OneToOne
	@JoinColumn(name="message_tag", nullable=true)
	private Message messageTag;
	
	@OneToOne
	@JoinColumn(name="user_tag", nullable=true)
	private User userTag;
	
	
	public Tag() {
		super();
	}
	public Tag( String name, Message messageTag, User userTag) {
		super();
		this.name = name;
		this.messageTag = messageTag;
		this.userTag = userTag;
	}
	public Message getMessageTag() {
		return messageTag;
	}
	public void setMessageTag(Message messageTag) {
		this.messageTag = messageTag;
	}
	public User getUserTag() {
		return userTag;
	}
	public void setUserTag(User userTag) {
		this.userTag = userTag;
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
	
	
}
