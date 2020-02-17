package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Photo;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.PhotoRepository;
@Service
public class PhotoService implements PhotoServiceInterface {
	

	@Autowired
	PhotoRepository photoRepository;

	@Override
	public List<Photo> findByParent(Photo parent) {
		return null;
	}

	@Override
	public Photo findOne(Integer photoId) {
		return photoRepository.findOne(photoId);
	}

	@Override
	public List<Photo> findAll() {
		return photoRepository.findAll();
	}

	@Override
	public Photo save(Photo photo) {
		return photoRepository.save(photo);
	}

	@Override
	public void remove(Integer id) {
		photoRepository.delete(id);
		
	}
	

}
