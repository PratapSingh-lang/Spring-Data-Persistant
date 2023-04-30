package com.hibernetProject.hibernetProject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    
    @OneToMany(mappedBy = "role"
//    		, cascade = CascadeType.ALL
//    		,cascade=CascadeType.REMOVE
    		,cascade = CascadeType.PERSIST
//    		, orphanRemoval = true
    		, targetEntity = Privilege.class)
    private List<Privilege> privileges = new ArrayList<>();
    
    // getters and setters
}

