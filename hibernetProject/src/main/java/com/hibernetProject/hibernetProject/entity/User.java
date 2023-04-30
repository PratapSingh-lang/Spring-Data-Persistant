package com.hibernetProject.hibernetProject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "users")
@ToString(includeFieldNames=false, exclude={"roles"})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name")
    private String userName;
    
    private String password;
    
    @OneToMany(mappedBy = "user"
//    		, cascade = CascadeType.ALL
//    		,cascade=CascadeType.REMOVE
    		,cascade=CascadeType.PERSIST
//    		, orphanRemoval = true
    		, targetEntity = Role.class)
    private List<Role> roles = new ArrayList<>();
    
    // getters and setters
}

