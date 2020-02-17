package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.MessageRepository;

@Service
public class MessageService implements MessageServiceInterface {

	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public List<Message> findByParent(Message parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message findOne(Integer messageId) {
		// TODO Auto-generated method stub
		return messageRepository.findOne(messageId);
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return messageRepository.findAll();
	}

	@Override
	public Message save(Message message) {
		// TODO Auto-generated method stub
		return messageRepository.save(message);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		messageRepository.delete(id);
	}

	@Override
	public List<Message> findMessagesForAccount(Integer messageId) {
		// TODO Auto-generated method stub
		return messageRepository.findMessagesForAccount(messageId);
	}

	@Override
	public Message findMessageByGmailId(String gmailId) {
		// TODO Auto-generated method stub
		return messageRepository.findMessagesByGmailId(gmailId);
	}
}
