package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;

public interface AttachmentServiceInterface {

	List<Attachment> findByParent(Attachment parent);
	
	Attachment findOne(Integer attachmentId);
	
	List<Attachment> findAll();
	
	Attachment save(Attachment attachment);
	
	void remove(Integer id);
}
