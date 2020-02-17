package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{
	
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findByParent(User parent) {
		return null;
	}

	@Override
	public User findOne(Integer userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void remove(Integer id) {
		userRepository.delete(id);
		
	}

}
