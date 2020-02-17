package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Attachment;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.AttachmentRepository;

@Service
public class AttachmentService implements AttachmentServiceInterface {

	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Override
	public List<Attachment> findByParent(Attachment parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attachment findOne(Integer attachmentId) {
		// TODO Auto-generated method stub
		return attachmentRepository.findOne(attachmentId);
	}

	@Override
	public List<Attachment> findAll() {
		// TODO Auto-generated method stub
		return attachmentRepository.findAll();
	}

	@Override
	public Attachment save(Attachment attachment) {
		// TODO Auto-generated method stub
		return attachmentRepository.save(attachment);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		attachmentRepository.delete(id);
	}

}
