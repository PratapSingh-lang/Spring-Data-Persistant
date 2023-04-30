package com.hibernetProject.hibernetProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernetProject.hibernetProject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
