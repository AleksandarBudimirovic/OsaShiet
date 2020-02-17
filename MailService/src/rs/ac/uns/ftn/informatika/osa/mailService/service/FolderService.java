package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.FolderRepository;

@Service
public class FolderService implements FolderServiceInterface {

	@Autowired
	FolderRepository folderRepository;
	
	@Override
	public List<Folder> findByParent(Folder parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Folder findOne(Integer folderId) {
		// TODO Auto-generated method stub
		return folderRepository.findOne(folderId);
	}

	@Override
	public List<Folder> findAll() {
		// TODO Auto-generated method stub
		return folderRepository.findAll();
	}

	@Override
	public Folder save(Folder folder) {
		// TODO Auto-generated method stub
		return folderRepository.save(folder);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		folderRepository.delete(id);
	}

	@Override
	public Folder findInboxForAccount(Integer id) {
		// TODO Auto-generated method stub
		return folderRepository.findInboxForAccount(id);
	}

}
