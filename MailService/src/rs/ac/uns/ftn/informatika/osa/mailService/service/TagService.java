package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.TagRepository;

@Service
public class TagService implements TagServiceInterface {
	
	@Autowired
	TagRepository tagRepository;

	@Override
	public List<Tag> findByParent(Tag parent) {
		return null;
	}

	@Override
	public Tag findOne(Integer tagId) {
		return tagRepository.findOne(tagId);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void remove(Integer id) {
		tagRepository.delete(id);
		
	}

}
