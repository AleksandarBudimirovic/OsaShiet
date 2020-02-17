package rs.ac.uns.ftn.informatika.osa.mailService.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;

public class MessageDTO implements Serializable {
	
	   private int id;
	   private String from;
	   private String to;
	   private Date dateTime;
	   private String subject;
	   private String content;
	   private String cc;
	   private String bcc;
	   private ArrayList<AttachmentDTO> attachments;
	   private ArrayList<TagDTO> tags=new ArrayList<>();
	   private AccountDTO account;
	   private FolderDTO folder;
	   private boolean unread;
	   private boolean draft;
	    
		public MessageDTO() {
			super();
		}

		public MessageDTO(int id, String from, String to, Date dateTime, String subject, String content,
				String cc, String bcc, ArrayList<AttachmentDTO> attachments, AccountDTO account,
				FolderDTO folder, boolean draft, boolean unread,ArrayList<TagDTO> tags) {
			super();
			this.id = id;
			this.from = from;
			this.to = to;
			this.dateTime = dateTime;
			this.subject = subject;
			this.content = content;
			this.cc = cc;
			this.bcc = bcc;
			this.attachments = attachments;
			this.account = account;
			this.folder = folder;
			this.draft=draft;
			this.unread=unread;
			this.tags=tags;
		}
		
		public MessageDTO(int id, String from, String to, Date dateTime, String subject, String content,
				String cc, String bcc, AccountDTO account,
				FolderDTO folder,boolean draft,boolean unread,ArrayList<TagDTO> tags) {
			super();
			this.id = id;
			this.from = from;
			this.to = to;
			this.dateTime = dateTime;
			this.subject = subject;
			this.content = content;
			this.cc = cc;
			this.bcc = bcc;
			this.account = account;
			this.folder = folder;
			this.draft=draft;
			this.unread=unread;
			this.tags=tags;
		}
		//new account i folder??
		//cc i bcc iz stringa u listu ili promeniti da bude string u androidu
		public MessageDTO(Message m){
			this(m.getId(), m.getFrom(), m.getTo(), m.getDateTime(), m.getSubject(), m.getContent(), m.getCc(), 
					m.getBcc(), new AccountDTO(), new FolderDTO(), m.isDraft(), m.isUnread(),getTagsDTO(m.getMessageTag()));
		}
		
		public MessageDTO(Message m, List<Attachment> attachments){
			this(m.getId(), m.getFrom(), m.getTo(), m.getDateTime(), m.getSubject(), m.getContent(), m.getCc(), 
					m.getBcc(),getAttachmentsDTO(attachments), new AccountDTO(), new FolderDTO(), m.isDraft(),m.isUnread(),getTagsDTO(m.getMessageTag()));
		}
		
		public static ArrayList<AttachmentDTO> getAttachmentsDTO(List<Attachment> att){
			ArrayList<AttachmentDTO> attDTO=new ArrayList<AttachmentDTO>();
			for(Attachment a: att) {
				attDTO.add(new AttachmentDTO(a));
			}
			return attDTO;
		}
		public static ArrayList<TagDTO> getTagsDTO(List<Tag> tags){
			ArrayList<TagDTO> ret=new ArrayList<>();
			if(tags!=null) {
				for(Tag t: tags)
					ret.add(new TagDTO(t.getId(),t.getName(),t));
			}
			return ret;
		}
		public ArrayList<TagDTO> getTags() {
			return tags;
		}

		public void setTags(ArrayList<TagDTO> tags) {
			this.tags = tags;
		}

		public boolean isUnread() {
			return unread;
		}

		public void setUnread(boolean unread) {
			this.unread = unread;
		}

		public boolean isDraft() {
			return draft;
		}

		public void setDraft(boolean draft) {
			this.draft = draft;
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

		public ArrayList<AttachmentDTO> getAttachments() {
			return attachments;
		}

		public void setAttachments(ArrayList<AttachmentDTO> attachments) {
			this.attachments = attachments;
		}

		public AccountDTO getAccount() {
			return account;
		}

		public void setAccount(AccountDTO account) {
			this.account = account;
		}

		public FolderDTO getFolder() {
			return folder;
		}

		public void setFolder(FolderDTO folder) {
			this.folder = folder;
		}

		@Override
		public String toString() {
			return "MessageDTO [id=" + id + ", from=" + from + ", to=" + to + ", dateTime=" + dateTime + ", subject="
					+ subject + ", content=" + content + ", cc=" + cc + ", bcc=" + bcc + ", attachments=" + attachments
					+ ", account=" + account + ", folder=" + folder + "]";
		}


		
}
