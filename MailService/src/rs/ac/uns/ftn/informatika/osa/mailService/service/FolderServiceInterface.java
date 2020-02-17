package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;

public interface FolderServiceInterface {

	List<Folder> findByParent(Folder parent);
	
	Folder findOne(Integer folderId);
	
	List<Folder> findAll();
	
	Folder save(Folder folder);
	
	void remove(Integer id);
	
	Folder findInboxForAccount(Integer id);
	
}
