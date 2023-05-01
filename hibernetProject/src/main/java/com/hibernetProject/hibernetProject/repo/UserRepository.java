package com.hibernetProject.hibernetProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param $field
	 * @return  Optional<User>
	 * @param userName
	 * @return
	 */
	Optional<User> findByUserName(String userName);

	/**
	 * @param $field
	 * @return  Optional<User>
	 * @param username
	 * @return
	 */
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.userName = ?1")
	Optional<User> findByUsernameWithRoles(String username);
    
}
