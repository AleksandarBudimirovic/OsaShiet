package rs.ac.uns.ftn.informatika.osa.mailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import rs.ac.uns.ftn.informatika.osa.mailService.controller.MessageController;
import rs.ac.uns.ftn.informatika.osa.mailService.entity.EventLog;
import rs.ac.uns.ftn.informatika.osa.mailService.mailUtils.Mail;

@SpringBootApplication
public class MailServiceApplication {

	public static EventLog eventLog=EventLog.getInstance();
	
	public static void main(String[] args) {
		eventLog.setId(1);
		SpringApplication.run(MailServiceApplication.class, args);
	}
}
