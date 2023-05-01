package com.hibernetProject.hibernetProject.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.Privilege;
import com.hibernetProject.hibernetProject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * @param $field
	 * @return  Set<Role>
	 * @param privilege
	 * @return
	 */
	@Query("SELECT r FROM Role r JOIN r.privileges p WHERE p = :privilege")
    Set<Role> findByPrivileges(@Param("privilege") Privilege privilege);

	/**
	 * @param $field
	 * @return  Optional<Role>
	 * @param name
	 * @return
	 */
	Optional<Role> findByName(String name);

	
	
}

