package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Photo;

public interface PhotoServiceInterface {
	
    List<Photo> findByParent(Photo parent);
	
	Photo findOne(Integer photoId);
	
	List<Photo> findAll();
	
	Photo save(Photo photo);
	
	void remove(Integer id);

}
