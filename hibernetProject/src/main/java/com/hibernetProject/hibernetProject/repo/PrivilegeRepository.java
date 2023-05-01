package com.hibernetProject.hibernetProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	/**
	 * @param $field
	 * @return  Optional<Privilege>
	 * @param name
	 * @return
	 */
	Optional<Privilege> findByName(String name);
    
}

