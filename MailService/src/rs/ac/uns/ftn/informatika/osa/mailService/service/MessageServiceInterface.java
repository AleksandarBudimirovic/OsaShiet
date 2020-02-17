package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;

public interface MessageServiceInterface {

	List<Message> findByParent(Message parent);
	
	Message findOne(Integer messageId);
	
	List<Message> findAll();
	
	Message save(Message message);
	
	void remove(Integer id);
	
	List<Message> findMessagesForAccount(Integer messageId);

	Message findMessageByGmailId(String gmailId);
	
}
