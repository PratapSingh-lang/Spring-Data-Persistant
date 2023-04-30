package com.hibernetProject.hibernetProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}

