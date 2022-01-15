package com.citius.userms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citius.userms.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	List<User> findAllByRolesName(String role);

}
