package rs.ac.uns.ftn.informatika.osa.mailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
