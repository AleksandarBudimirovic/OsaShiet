package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.ContactRepository;

@Service
public class ContactService implements ContactServiceInterface {

	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public List<Contact> findByParent(Contact parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findOne(Integer contactId) {
		// TODO Auto-generated method stub
		return contactRepository.findOne(contactId);
	}

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Override
	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepository.save(contact);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		contactRepository.delete(id);
	}

}
