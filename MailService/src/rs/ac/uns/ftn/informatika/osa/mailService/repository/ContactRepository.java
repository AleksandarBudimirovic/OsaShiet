package rs.ac.uns.ftn.informatika.osa.mailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
