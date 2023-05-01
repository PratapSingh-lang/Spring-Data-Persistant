package com.hibernetProject.hibernetProject.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bhanu.pratap
 *
 */
@Entity
//@ToString
//@ToString(includeFieldNames=false, exclude={"user"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "role_privilege",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    @JsonManagedReference
//    @JsonIgnoreProperties("roles")
    @JsonIgnore
    private Set<Privilege> privileges = new HashSet<>();
    
    public void removePrivilege(Privilege privilege) {
        privileges.remove(privilege);
        privilege.getRoles().remove(this);
    }
    
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    @JsonIgnore
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    // ...

	/*
	 * @JsonIgnore public Set<User> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<User> users) { this.users = users; }
	 */
    // getters and setters
}
