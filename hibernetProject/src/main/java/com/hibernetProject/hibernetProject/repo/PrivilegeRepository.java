package com.hibernetProject.hibernetProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    
}

