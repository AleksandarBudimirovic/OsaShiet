package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Tag;

public interface TagServiceInterface {
	
	 	List<Tag> findByParent(Tag parent);
		
		Tag findOne(Integer tagId);
		
		List<Tag> findAll();
		
		Tag save(Tag tag);
		
		void remove(Integer id);

}
