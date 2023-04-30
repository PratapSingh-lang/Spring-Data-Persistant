package com.hibernetProject.hibernetProject.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public User addRoleToUser(Long userId, Role role) {
		User user = getUserById(userId);
		role.setUser(user);
		user.getRoles().add(role);
		return userRepository.save(user);
	}

	// remove a role from a user
	public User removeRoleFromUser(Long userId, Long roleId) {
		User user = getUserById(userId);
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
		user.getRoles().remove(role);
		role.setUser(null);
		roleRepository.delete(role);
		return userRepository.save(user);
	}

	// add a privilege to a role
	public Role addPrivilegeToRole(Long roleId, Privilege privilege) {
		Role role = getRoleById(roleId);
		privilege.setRole(role);
		role.getPrivileges().add(privilege);
		return roleRepository.save(role);
	}

	// remove a privilege from a role
	public Role removePrivilegeFromRole(Long roleId, Long privilegeId) {
		Role role = getRoleById(roleId);
		Privilege privilege = privilegeRepository.findById(privilegeId)
				.orElseThrow(() -> new EntityNotFoundException("Privilege not found with id: " + privilegeId));
		role.getPrivileges().remove(privilege);
		privilege.setRole(null);
		privilegeRepository.delete(privilege);
		return roleRepository.save(role);
	}

	// get a role by id
	public Role getRoleById(Long roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
	}

	// delete a user by id
	@Transactional
	public void deleteUser(Long userId) {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
//		List<Role> roles = user.getRoles();
//		
//		for (Role role : roles) {
//			log.info("role : {}", role);
//			List<Privilege> privileges = role.getPrivileges();
//			role.getPrivileges().removeAll(privileges);
////			for (Privilege privilege : privileges) {
//////                role.removePrivilege(privilege);
////				log.info("Privilege : {}", privilege);
////				role.getPrivileges().remove(privilege);
////			}
////			role.setUser(null);
////			user.getRoles().remove(role);
//		}
//		user.getRoles().removeAll(roles);
////		user.setRoles(new ArrayList<>());
//		userRepository.delete(user);
//		
		
		 User user = userRepository.findById(userId).orElse(null);
		 
	        if (user != null) {
	            // Remove the association between the user and its roles
//	            for (Role role : user.getRoles()) {
//	                role.setUser(null);
////	                roleRepository.save(role);
//	            }
	            user.getRoles().forEach(r->{
	   			 r.setUser(null);
	   		 });
	            // Delete the user
	            userRepository.delete(user);
	        }
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Role createRole(Role role) {
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

	@Transactional
	public void deleteRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
//		role.setPrivileges(null);
		role.getPrivileges().forEach(p->{
			p.setRole(null);
			
		});
//		List<Privilege> privileges = role.getPrivileges();
//		if (!privileges.isEmpty()) {
//			for (Privilege privilege : privileges) {
//				role.getPrivileges().remove(privilege);
////	            privilege.getRole().remove(role);
//				privilege.setRole(null);
//				privilegeRepository.save(privilege);
//			}
//		}
		roleRepository.delete(role);
	}

	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

	public Privilege getPrivilegeById(Long id) {
		return privilegeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Privilege", "id", id));
	}

	public Privilege createPrivilege(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	public Privilege updatePrivilege(Long id, Privilege privilegeDetails) {
		Privilege privilege = getPrivilegeById(id);
		privilege.setName(privilegeDetails.getName());
		privilege.setDescription(privilegeDetails.getDescription());
//		    userService.updatePrivilege(privilege);
		return privilegeRepository.save(privilege);
	}

	public void deletePrivilege(Long id) {
		Privilege privilege = getPrivilegeById(id);
//		for (Role role : privilege.getRoles()) {
//	        role.removePrivilege(privilege);
//	        roleRepository.save(role);
//	    }
		privilegeRepository.delete(privilege);
	}

	public User addRoleToUser(Long userId, Long roleId) {
	    User user = userRepository.findById(userId).orElse(null);
	    if (user == null) {
	        return null;
	    }

	    Role role = roleRepository.findById(roleId).orElse(null);
	    if (role == null) {
	        return null;
	    }
	    List<Role> roles = new ArrayList<>();
	    roles.add(role);
	    user.setRoles(roles);
	    userRepository.save(user);
	    return user;
	}

	public User addRolesToUser(Long userId, List<Long> roleIds) {
	    User user = userRepository.findById(userId).orElse(null);
	    if (user == null) {
	        return null;
	    }
	    
	    List<Role> roles = roleRepository.findAllById(roleIds);
	    for (Role role : roles) {
	    	log.info("Role: {}", role);
	    	role.setUser(user);
	    }
	    user.getRoles().addAll(roles);
	    userRepository.save(user);
	    return user;
	}

//	@Override
	public Role addPrivilegesToRole(Long roleId, List<Long> privilegeIds) {
	    Role role = roleRepository.findById(roleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

	    List<Privilege> privileges = privilegeRepository.findAllById(privilegeIds);
	    if (privileges.isEmpty()) {
	        throw new ResourceNotFoundException("Privileges", "ids", privilegeIds);
	    }

	    for (Privilege privilege : privileges) {
	    	log.info("Privilege: {}", privilege);
	        privilege.setRole(role);
	    }
	    boolean addAll = role.getPrivileges().addAll(privileges);
	    log.info("value of add all is {}" ,addAll);
	    log.info("Role : {}", role);
	    return roleRepository.save(role);
	}



// // delete a user by id
//    public void deleteUser(Long userId) {
//        User user = getUserById(userId);
//        for (Role role : user.getRoles()) {
//            for (List<Privilege> privileges : role.getPrivileges()) {
//                role.getPrivileges().remove(privilege);
//            }
//            role.setUser(null);
//        }
//        user.setRoles(new HashSet<>());
//        userRepository.delete(user);
//    }

}
