package com.hibernetProject.hibernetProject.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(includeFieldNames=false, exclude={"role"})
public class Privilege {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    @JsonBackReference
//    @JsonIgnoreProperties("privileges")
//    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

	/*
	 * public Set<Role> getRoles() { return roles; }
	 */
}

