package rs.ac.uns.ftn.informatika.osa.mailService.mailUtils;

import javax.activation.FileDataSource;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.pop3.POP3Folder;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import rs.ac.uns.ftn.informatika.osa.mailService.controller.MessageController;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;
import rs.ac.uns.ftn.informatika.osa.mailService.service.MessageServiceInterface;


@RestController
@RequestMapping(value="/")
public class Mail {
//	public static void main(String[] args) {
//		String host = c;
//	    String mailStoreType = "pop3";
//	    String username = "testpmsu@gmail.com";
//	    String password = "TestPMSU1";
//
//	
//	ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Message> msd = fetch(host, mailStoreType, username, password,true);
//	System.out.println("size: " + msd.size());
//	}
	@Autowired
	private MessageServiceInterface messageService;
	
	private static String uri="F:\\Programming\\PMSdoPost\\ValentininaOsaSklj\\OSASklj\\OSASklj2\\MailService\\data\\";
	@RequestMapping("/fetch")
	public ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Message>  fetch(Account account, boolean firstTime, List<rs.ac.uns.ftn.informatika.osa.mailService.entity.Message> existingMessages) {
		
		ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Message> userMessages = new ArrayList<>();
		ArrayList<rs.ac.uns.ftn.informatika.osa.mailService.entity.Message> returnMessages = new ArrayList<>();
        
		try {
			Properties properties = new Properties();
	        properties.put("mail.store.protocol", "pop3");
	        properties.put("mail.pop3.host", "mail.pop3.host");
	        properties.put("mail.pop3.port", "995");
	        properties.put("mail.pop3.starttls.enable", "true");
//	        properties.put("mail.debug", "true");
	        
	        Session emailSession = Session.getDefaultInstance(properties);
	      //  emailSession.setDebug(true);
	        
	        Store store = emailSession.getStore("pop3s");
	        
	        store.connect("pop.gmail.com", "dopost123@gmail.com", "sfdopost2019");
	        
	        Folder emailFolder = store.getFolder("INBOX");
	        emailFolder.open(Folder.READ_ONLY);
	        Message messages[];
	        
            FetchProfile fp = new FetchProfile();
            fp.add(UIDFolder.FetchProfileItem.UID);
            emailFolder.fetch(emailFolder.getMessages(), fp);

            POP3Folder pf =(POP3Folder)emailFolder;         

            messages = emailFolder.getMessages();

//                    String uid = pf.getUID(message);
//                    System.out.println(uid)
	        
	        if(messages!=null) {
		        for(int i = 0; i < messages.length; i++) {
		        	Message message = messages[i];
		        	
		        	String uid = pf.getUID(message);
                    
		        	rs.ac.uns.ftn.informatika.osa.mailService.entity.Message modelMessage = new rs.ac.uns.ftn.informatika.osa.mailService.entity.Message();
		        	Address[] a;
		        	
//		        	modelMessage.setContent(getTextFromMessage(message));
		        	
		        	String contentType = message.getContentType();
		        	String messageContent = "";
		        	
//		            CONTENT
		        	
		        	String attachFiles = "";
		        	if(contentType.contains("multipart")) {
		        		Multipart multiPart = (Multipart) message.getContent();
	                    int numberOfParts = multiPart.getCount();
	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                            // this part is attachment
	                            String fileName = part.getFileName();
	                            
	                            attachFiles += fileName + ", ";
	                            part.saveFile(uri + File.separator + fileName);
	                            byte[] bFile = Files.readAllBytes(new File(uri + fileName).toPath());
	                            String sFile = Base64.encodeBase64String(bFile);
	                            Attachment att = new Attachment();
	                            att.setId(att.hashCode());
	                            att.setName(fileName);
	                            att.setData(sFile);
	                            att.setMimeType(".jpg");
	                            ArrayList<Attachment> atts=new ArrayList<>();
	                            atts.add(att);
	                            modelMessage.setMessageAttachments(atts);
	                            
	                            File fl = new File("./data/"+ fileName);
	                            fl.delete();
	                            
	                        } else {
	                            // this part may be the message content
	                            messageContent = part.getContent().toString();
	                        }
	                    }
	                    //modelMessage.setContent(messageContent.split(">")[1].split("<")[0]);
	                    //System.out.println(getTextFromMessage(message));
	                    modelMessage.setContent(getTextFromMessage(message));
	                    if (attachFiles.length() > 1) {
	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
	                    }
		        	}else{
		        		modelMessage.setContent(getTextFromMessage(message));
		        	}
		        	
		        	
		        	if((a = message.getFrom()) != null) {
		        		StringBuilder sb = new StringBuilder();
		        		for(int j = 0; j < a.length; j++) {
		        			sb.append(a[j].toString());
		        		}
//	sb? =?UTF-8?Q?Boris_=C5=A0obota?= <sobota365@gmail.com>
//	sb? testpmsu@gmail.com
						if(sb.toString().contains("<")) {
							modelMessage.setFrom(sb.toString().split("<")[1].split(">")[0]);
						}else 
							modelMessage.setFrom(sb.toString());
		        	}
		        	
//		        	modelMessage.setId(modelMessage.hashCode());
		        	modelMessage.setSubject(message.getSubject());
		        	modelMessage.setDateTime(new java.sql.Date(message.getSentDate().getTime()));
//		        	modelMessage.setTo(message.getAllRecipients()[0].toString());
		        	modelMessage.setUnread(true);
		        	modelMessage.setCc("");
		        	modelMessage.setBcc("");
		        	modelMessage.setGmailId(uid);
		        	modelMessage.setAccount(account);
		        	
		        	ArrayList<String> toAddresses = new ArrayList<String>();
		        	Address[] recipients = message.getRecipients(Message.RecipientType.TO);
		        	StringBuilder recipientsString = new StringBuilder();
		        	for (Address address : recipients) {
		        	    recipientsString.append(address.toString());
		        	    recipientsString.append(", ");
		        	}       	
		        	
		        	modelMessage.setTo(recipientsString.toString());
		        	
		        	userMessages.add(modelMessage);
		        	
		        }    
		        
	        }
	        
			return userMessages;
		}catch(Exception e ) {
			e.printStackTrace();
			return returnMessages;
		}
	}

	public static boolean sendMail(rs.ac.uns.ftn.informatika.osa.mailService.entity.Message message, Account account) {	
		
		Properties props = new Properties();
		
		 props.put("mail.smtp.host", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", "smtp.gmail.com");
	     props.put("mail.smtp.port", "587");
	     props.put("mail.smtp.auth", "true");
	     
	     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(account.getInServerAddress(), account.getPassword());
	            }
	        });
	     
	     try {
//			MimeMessage msg = new MimeMessage(session);
//			String to = message.getTo();
//			InternetAddress[] adress = InternetAddress.parse(to, true);
//			msg.setRecipients(Message.RecipientType.TO, adress);
//			msg.setSubject(message.getSubject());
//			msg.setSentDate(message.getDateTime());
//			msg.setText(message.getContent());
//			msg.setHeader("XPriority", "1");
//			Transport.send(msg);
	    	 
	    	Message msg = new MimeMessage(session);
	    	msg.setFrom(new InternetAddress(message.getFrom()));
      		String to = message.getTo();
      		String bcc = message.getBcc();
      		String cc = message.getCc();
			InternetAddress[] address = InternetAddress.parse(to, true);
			InternetAddress[] addressBcc = InternetAddress.parse(bcc, true);
			InternetAddress[] addressCc = InternetAddress.parse(cc, true);
			
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setRecipients(Message.RecipientType.BCC, addressBcc);
			msg.setRecipients(Message.RecipientType.CC, addressCc);
			
			msg.setSubject(message.getSubject());
			if(message.getMessageAttachments()!=null) {
				if(message.getMessageAttachments().size() > 0) {
					attToFile(message);
				}
			}
			
			
			BodyPart msgBodyPart = new MimeBodyPart();
			msgBodyPart.setText(message.getContent());
			
			Multipart multipart = new MimeMultipart();
			
			multipart.addBodyPart(msgBodyPart);
			
			msgBodyPart = new MimeBodyPart();
			if(message.getMessageAttachments()!=null) {
				if(message.getMessageAttachments().size() > 0) {
					String filename = "./data/" + message.getMessageAttachments().get(0).getName();
					DataSource source = new FileDataSource(filename);
					msgBodyPart.setDataHandler(new DataHandler(source));
					msgBodyPart.setFileName(message.getMessageAttachments().get(0).getName());
					multipart.addBodyPart(msgBodyPart);			
					
					msg.setContent(multipart);
			}}else {
				msg.setText(message.getContent());
			}
			
			msg.setSentDate(message.getDateTime());
			
			Transport.send(msg);
			if(message.getMessageAttachments()!=null) {
				if(message.getMessageAttachments().size() > 0) {
					File f = new File("./data", message.getMessageAttachments().get(0).getName());
					f.delete();
				}
			}
			
			System.out.println("Mail has been sent successfully!");		
			return true;
			
		} catch (MessagingException e) {
			System.out.println("Unable to send an email. " + e);
			return false;
		}		
	}
	
	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
			MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
	public static void receiveEmail(String pop3Host,
	    String mailStoreType, String userName, String password){
	    //Set properties
	    Properties props = new Properties();
	    props.put("mail.store.protocol", "pop3");
	    props.put("mail.pop3.host", pop3Host);
	    props.put("mail.pop3.port", "995");
	    props.put("mail.pop3.starttls.enable", "true");
	 
	    // Get the Session object.
	    Session session = Session.getInstance(props);
	 
	    try {
	        //Create the POP3 store object and connect to the pop store.
		Store store = session.getStore("pop3s");
		store.connect(pop3Host, userName, password);
	 
		//Create the folder object and open it in your mailbox.
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
	 
		//Retrieve the messages from the folder object.
		Message[] messages = emailFolder.getMessages();
	 
		//Iterate the messages
		for (int i = 0; i < messages.length; i++) {
		   Message message = messages[i];
		   Address[] toAddress = 
	             message.getRecipients(Message.RecipientType.TO);
	 
		     //Iterate multiparts
		     Multipart multipart = (Multipart) message.getContent();
		     for(int k = 0; k < multipart.getCount(); k++){
		       BodyPart bodyPart = multipart.getBodyPart(k);  
		       InputStream stream = 
	                             (InputStream) bodyPart.getInputStream();  
		       BufferedReader bufferedReader = 
		    	   new BufferedReader(new InputStreamReader(stream));  
	   
		      }  
		   }
	 
		   //close the folder and store objects
		   emailFolder.close(false);
		   store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		} catch (Exception e) {
		       e.printStackTrace();
		}
	 
	}
	
	private static void attToFile(rs.ac.uns.ftn.informatika.osa.mailService.entity.Message message) {
		 Attachment att= message.getMessageAttachments().get(0);
         String name = att.getName();
         byte[] decodedString = Base64.decodeBase64(att.getData());
         String FILEPATH ="/" + att.getName();
         File file = new File("./data", att.getName());
         
         try {
        	 OutputStream os = new FileOutputStream(file);
        	 os.write(decodedString);
        	 os.close();
         }catch(Exception e) {
        	 e.printStackTrace();
         }
	}
	
	public static void receiveEmailAtt(String pop3Host, String mailStoreType, String userName, String password){
	    //Set properties
	    Properties props = new Properties();
	    props.put("mail.store.protocol", "pop3");
	    props.put("mail.pop3.host", pop3Host);
	    props.put("mail.pop3.port", "995");
	    props.put("mail.pop3.starttls.enable", "true");
	 
	    // Get the Session object.
	    Session session = Session.getInstance(props);
	 
	    try {
	        //Create the POP3 store object and connect to the pop store.
		Store store = session.getStore("pop3s");
		store.connect(pop3Host, userName, password);
	 
		//Create the folder object and open it in your mailbox.
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
	 
		//Retrieve the messages from the folder object.
		Message[] messages = emailFolder.getMessages();
	 
		//Iterate the messages
		for (int i = 0; i < messages.length; i++) {
		   Message message = messages[i];
		   Address[] toAddress = 
	             message.getRecipients(Message.RecipientType.TO); 
	 
	 
		     //Iterate multiparts
		     Multipart multipart = (Multipart) message.getContent();
		     for(int k = 0; k < multipart.getCount(); k++){
		       BodyPart bodyPart = multipart.getBodyPart(k);  
		       InputStream stream = 
	                             (InputStream) bodyPart.getInputStream();  
		       BufferedReader bufferedReader = 
		    	   new BufferedReader(new InputStreamReader(stream));  
	 
		    	   System.out.println();  
		      }  
		   }
	 
		   //close the folder and store objects
		   emailFolder.close(false);
		   store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		} catch (Exception e) {
		       e.printStackTrace();
		}
	 
	}
	
}
