package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

import rs.ac.uns.ftn.informatika.osa.mailService.dto.MessageDTO;

@Entity                
@Table(name="message")
public class Message implements Serializable, Comparable<Message>{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="message_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="sender", unique=false, nullable=true)
	private String from;
	
	@Column(name="recipient", unique=false, nullable=true)
	private String to;
	
	@Column(name="cc", unique=false, nullable=true)
	private String cc;
	
	@Column(name="bcc", unique=false, nullable=true)
	private String bcc;
	
	@Column(name="date_time", unique=false, nullable=true)
	private Date dateTime;
	
	@Column(name="subject", unique=false, nullable=true)
	private String subject;
	
	@Column(name="content", unique=false, nullable=true, columnDefinition = "longtext")
	private String content;
	
	@Column(name="gmail_id", unique=false, nullable=true, columnDefinition = "longtext")
	private String gmailId;
	
	@Column(name="unread", unique=false, nullable=true)
	private boolean unread;
	
	@Column(name="draft", unique=false, nullable=true)
	private boolean draft;
	
	@OneToOne
	@JoinColumn(name="account_message_id", nullable=true)
	private Account account;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="messages_id", nullable=true)
	private Folder folderMessage;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="messageTag")
	private List<Tag> messageTag;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="messageAttachment")
	private List<Attachment> messageAttachments;
	
	
	public Message() {
		super();
	}
	public Message( String from, String to, String cc, String bcc, Date dateTime, String subject, String content,
			boolean unread, Folder folderMessage, ArrayList<Tag> messageTag,
			ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment> messageAttachments, boolean draft) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
		this.folderMessage = folderMessage;
		this.messageTag = messageTag;
		this.messageAttachments = messageAttachments;
		this.draft=draft;
	}
	public Message( String from, String to, String cc, String bcc, Date dateTime, String subject, String content,
			boolean unread, Folder folderMessage,  boolean draft,
			ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment> messageAttachments) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
		this.folderMessage = folderMessage;
		this.messageAttachments = messageAttachments;
		this.draft=draft;
	}
	public Message( String from, String to, String cc, String bcc, Date dateTime, String subject, String content,
			boolean unread, Folder folderMessage, ArrayList<Tag> messageTag,boolean draft) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
		this.folderMessage = folderMessage;
		this.messageTag = messageTag;
		this.draft=draft;
	}
	public boolean isDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}
	@JsonIgnore
	public void setMessageTag(List<Tag> messageTag) {
		this.messageTag = messageTag;
	}
	@JsonIgnore
	public void setMessageAttachments(List<Attachment> messageAttachments) {
		this.messageAttachments = messageAttachments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public Folder getFolderMessage() {
		return folderMessage;
	}
	public void setFolderMessage(Folder folderMessage) {
		this.folderMessage = folderMessage;
	}
	public List<Tag> getMessageTag() {
		return messageTag;
	}
	public void setMessageTag(ArrayList<Tag> messageTag) {
		this.messageTag = messageTag;
	}
	public List<Attachment> getMessageAttachments() {
		return messageAttachments;
	}
	public void setMessageAttachments(ArrayList<Attachment> messageAttachments) {
		this.messageAttachments = messageAttachments;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", dateTime="
				+ dateTime + ", subject=" + subject + ", content=" + content + ", gmailId=" + gmailId + ", unread="
				+ unread + ", draft=" + draft + ", account=" + account.getId() + ", folderMessage=" + folderMessage
				+ ", messageTag=" + messageTag + ", messageAttachments=" + messageAttachments + "]";
	}
	@Override
	public int compareTo(Message other) {
		// TODO Auto-generated method stub
		if(from!=null && other.getFrom()!=null) {
		int i = from.compareTo(other.getFrom());
	    if (i != 0) return i;
		}
	    if(subject!=null && other.getSubject()!=null) {
	    int i = subject.compareTo(other.getSubject());
	    if (i != 0) return i;
	    }
	    if(to!=null && other.getTo()!=null) {
	     int i = to.compareTo(other.getTo());
	    if (i != 0) return i;
	    }
	    if(content!= null && other.getContent()!=null) {
	    int i = content.compareTo(other.getContent());
	    if (i != 0) return i;
	    }
	    return dateTime.compareTo(other.getDateTime());
	}
	
}
