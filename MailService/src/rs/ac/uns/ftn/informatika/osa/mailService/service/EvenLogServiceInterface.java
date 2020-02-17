package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.EventLog;

public interface EvenLogServiceInterface {

	EventLog findByParent(EventLog parent);
	
	EventLog findOne(Integer contactId);
	
	List<EventLog> findAll();
	
	EventLog save(EventLog eventLog);
	
	void remove(Integer id);
}
