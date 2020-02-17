package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;

public interface AccountServiceInterface {

	List<Account> findByParent(Account parent);
	
	Account findOne(Integer accountId);
	
	List<Account> findAll();
	
	Account save(Account account);
	
	void remove(Integer id);
}
