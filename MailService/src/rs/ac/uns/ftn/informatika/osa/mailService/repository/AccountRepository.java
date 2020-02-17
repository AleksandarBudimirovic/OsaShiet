package rs.ac.uns.ftn.informatika.osa.mailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
