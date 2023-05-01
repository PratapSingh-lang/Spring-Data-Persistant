package com.hibernetProject.hibernetProject.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hibernetProject.hibernetProject.entity.Privilege;
import com.hibernetProject.hibernetProject.entity.Role;
import com.hibernetProject.hibernetProject.entity.User;
import com.hibernetProject.hibernetProject.exception.ResourceNotFoundException;
import com.hibernetProject.hibernetProject.repo.PrivilegeRepository;
import com.hibernetProject.hibernetProject.repo.RoleRepository;
import com.hibernetProject.hibernetProject.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	Logger log = LoggerFactory.getLogger(UserService.class);
	// get all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create a user
	public User createUser(User user) {
		Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("A user with that username already exists");
        }

        return userRepository.save(user);
	}

	// get a user by id
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
	}

	// update a user by id
	public User updateUser(Long userId, User user) {
		User existingUser = getUserById(userId);
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		existingUser.setRoles(user.getRoles());
		return userRepository.save(existingUser);
	}

	// delete a user by id
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }

	// add a role to a user
	/*
	 * public User addRoleToUser(Long userId, Role role) { User user =
	 * getUserById(userId); role.setUser(user); user.getRoles().add(role); return
	 * userRepository.save(user); }
	 */

	// remove a role from a user
	/*
	 * public User removeRoleFromUser(Long userId, Long roleId) { User user =
	 * getUserById(userId); Role role = roleRepository.findById(roleId)
	 * .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " +
	 * roleId)); user.getRoles().remove(role); role.setUser(null);
	 * roleRepository.delete(role); return userRepository.save(user); }
	 */

	// add a privilege to a role
	/*
	 * public Role addPrivilegeToRole(Long roleId, Privilege privilege) { Role role
	 * = getRoleById(roleId); privilege.setRole(role);
	 * role.getPrivileges().add(privilege); return roleRepository.save(role); }
	 */

	// remove a privilege from a role
	/*
	 * public Role removePrivilegeFromRole(Long roleId, Long privilegeId) { Role
	 * role = getRoleById(roleId); Privilege privilege =
	 * privilegeRepository.findById(privilegeId) .orElseThrow(() -> new
	 * EntityNotFoundException("Privilege not found with id: " + privilegeId));
	 * role.getPrivileges().remove(privilege); privilege.setRole(null);
	 * privilegeRepository.delete(privilege); return roleRepository.save(role); }
	 */

	// get a role by id
	public Role getRoleById(Long roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
	}

	// delete a user by id
//	@Transactional
	public void deleteUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info("User with roles : {}", user);
            user.getRoles().clear();
            log.info("User : {}", user);
            userRepository.delete(user);
        } else {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Role createRole(Role role) {
		 Optional<Role> existingRole = roleRepository.findByName(role.getName());
	        if (existingRole.isPresent()) {
	            throw new IllegalArgumentException("A role with that name already exists");
	        }

	        return roleRepository.save(role);
	}

	public Role updateRole(Long id, Role role) {
		Role existingRole = roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
		existingRole.setName(role.getName());
		existingRole.setDescription(role.getDescription());
		existingRole.setPrivileges(role.getPrivileges());
		return roleRepository.save(existingRole);
	}

//	@Transactional
	public void deleteRole(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.getUsers().clear();
            role.getPrivileges().clear();
            roleRepository.delete(role);
        } else {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
	}

	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

	public Privilege getPrivilegeById(Long id) {
		return privilegeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Privilege", "id", id));
	}

	public Privilege createPrivilege(Privilege privilege) {
		 Optional<Privilege> existingPrivilege = privilegeRepository.findByName(privilege.getName());
	        if (existingPrivilege.isPresent()) {
	            throw new IllegalArgumentException("A privilege with that name already exists");
	        }

	        return privilegeRepository.save(privilege);
	}

	public Privilege updatePrivilege(Long id, Privilege privilegeDetails) {
		Privilege privilege = getPrivilegeById(id);
		privilege.setName(privilegeDetails.getName());
		privilege.setDescription(privilegeDetails.getDescription());
//		    userService.updatePrivilege(privilege);
		return privilegeRepository.save(privilege);
	}

	public void deletePrivilege(Long privilegeId) {
		Privilege privilege = privilegeRepository.findById(privilegeId)
                .orElseThrow(() -> new IllegalArgumentException("Privilege with id " + privilegeId + " not found"));

        Set<Role> roles = roleRepository.findByPrivileges(privilege);
        for (Role role : roles) {
            role.removePrivilege(privilege);
            roleRepository.save(role);
        }

        privilegeRepository.delete(privilege);
	}

	/*
	 * public User addRoleToUser(Long userId, Long roleId) { User user =
	 * userRepository.findById(userId).orElse(null); if (user == null) { return
	 * null; }
	 * 
	 * Role role = roleRepository.findById(roleId).orElse(null); if (role == null) {
	 * return null; } List<Role> roles = new ArrayList<>(); roles.add(role);
	 * user.setRoles(roles); userRepository.save(user); return user; }
	 */

	public User addRolesToUser(Long userId, List<Long> roleIds) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        Set<Role> roles = user.getRoles();
        if (roles == null) {
            roles = new HashSet<>();
        }
        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new IllegalArgumentException("Role with id " + roleId + " not found"));
            roles.add(role);
        }
        user.setRoles(roles);
        return userRepository.save(user);
	}

	public Role addPrivilegesToRole(Long roleId, List<Long> privilegeIds) {

	    Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role with id " + roleId + " not found"));
	    log.info("Role : {}", role);
        Set<Privilege> privileges = role.getPrivileges();
        if (privileges == null) {
            privileges = new HashSet<>();
        }
        for (Long privilegeId : privilegeIds) {
            Privilege privilege = privilegeRepository.findById(privilegeId)
                    .orElseThrow(() -> new IllegalArgumentException("Privilege with id " + privilegeId + " not found"));
            log.info("Privilege {}" ,privilege);
    	    
            privileges.add(privilege);
        }
        role.setPrivileges(privileges);
        log.info("Role after setting priviliges : {}", role);
        return roleRepository.save(role);
	}

	/**
	 * @param $field
	 * @return  Set<Privilege>
	 * @param roleName
	 * @return
	 */
	public Set<Privilege> getPrivilegesForRole(String roleName) {
		Optional<Role> role = roleRepository.findByName(roleName);
        if (role != null) {
            return role.get().getPrivileges();
        }
        return null;
	}

	/**
	 * @param $field
	 * @return  Set<Privilege>
	 * @param roleId
	 * @return
	 */
	public Set<Privilege> findPrivilegesByRoleId(Long roleId) {
		Role role = roleRepository.findById(roleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
	        return role.getPrivileges();
	}

	/**
	 * @param $field
	 * @return  Optional<User>
	 * @param username
	 * @return
	 */
	public ResponseEntity<UserResponse> findByUsernameWithRoles(String username) {
		Optional<User> optionalUser = userRepository.findByUsernameWithRoles(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponse userResponse = new UserResponse(user.getId(), user.getUserName(), 
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
	}



}
