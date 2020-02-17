package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.EventLog;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.ContactRepository;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.EventLogRepository;

@Service
public class EventLogService implements EvenLogServiceInterface{

	@Autowired
	EventLogRepository logRepository;
	
	@Override
	public EventLog findByParent(EventLog parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventLog findOne(Integer contactId) {
		// TODO Auto-generated method stub
		return logRepository.findOne(contactId);
	}

	@Override
	public List<EventLog> findAll() {
		// TODO Auto-generated method stub
		return logRepository.findAll();
	}

	@Override
	public EventLog save(EventLog eventLog) {
		// TODO Auto-generated method stub
		return logRepository.save(eventLog);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		logRepository.delete(id);
	}

}
