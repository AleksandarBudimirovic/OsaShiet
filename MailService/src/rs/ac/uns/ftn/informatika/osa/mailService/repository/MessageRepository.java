package rs.ac.uns.ftn.informatika.osa.mailService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	@Query(value = "SELECT * FROM message where account_message_id= :messageId",  nativeQuery = true)
	List<Message> findMessagesForAccount(@Param("messageId") Integer messageId);
	
	@Query(value = "SELECT * FROM message where gmail_id= :gmailId",  nativeQuery = true)
	Message findMessagesByGmailId(@Param("gmailId") String gmailId );
	
}
