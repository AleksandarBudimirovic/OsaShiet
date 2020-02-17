package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Account;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.AccountRepository;

@Service
public class AccountService implements AccountServiceInterface {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<Account> findByParent(Account parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findOne(Integer accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findOne(accountId);
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public void remove(Integer id) {
		accountRepository.delete(id);	
	}
	
}
