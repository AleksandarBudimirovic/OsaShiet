package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;

public interface ContactServiceInterface {

	List<Contact> findByParent(Contact parent);
	
	Contact findOne(Integer contactId);
	
	List<Contact> findAll();
	
	Contact save(Contact contact);
	
	void remove(Integer id);
}
