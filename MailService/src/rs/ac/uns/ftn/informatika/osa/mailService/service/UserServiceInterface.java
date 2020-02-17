package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;

public interface UserServiceInterface {
	
	 	List<User> findByParent(User parent);
		
		User findOne(Integer userId);
		
		List<User> findAll();
		
		User save(User user);
		
		void remove(Integer id);

}
