package rs.ac.uns.ftn.informatika.osa.mailService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer>{
	
	@Query(value = "SELECT folder FROM folder where name='Inbox' and account_id=3",  nativeQuery = true)
	Folder findInboxForAccount(Integer id);

}
