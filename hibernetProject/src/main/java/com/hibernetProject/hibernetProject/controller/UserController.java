package com.hibernetProject.hibernetProject.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.hibernetProject.hibernetProject.entity.*;
import com.hibernetProject.hibernetProject.service.UserResponse;
import com.hibernetProject.hibernetProject.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "users", description = "Operations related to users")
public class UserController {
      
    @Autowired
    private UserService userService;

    @ApiOperation(value = "this is to check conection", response = List.class)
    @GetMapping("")
    public ResponseEntity<?> getUsers() {
    	return new ResponseEntity<>("this is hibernet", HttpStatus.OK);
    }
    @GetMapping("/users")
    @ApiOperation(value = "Get all users", response = List.class)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Get a user by ID", response = User.class)
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Create a new user", response = User.class)
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }
    
	/*
	 * @ApiOperation(value = "Add a role to a user", response = User.class)
	 * 
	 * @PostMapping("/users/{userId}/role") public ResponseEntity<User>
	 * addRoleToUser(@PathVariable Long userId, @RequestBody Role role) { User
	 * updatedUser = userService.addRoleToUser(userId, role); if (updatedUser ==
	 * null) { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(updatedUser); }
	 */
    
	/*
	 * @ApiOperation(value = "Add a role to a user by role Id", response =
	 * User.class)
	 * 
	 * @PostMapping("/users/{userId}/{roleId}") public ResponseEntity<User>
	 * addRoleToUserbyRoleId(@PathVariable Long userId, @PathVariable Long roleId) {
	 * User updatedUser = userService.addRoleToUser(userId, roleId); if (updatedUser
	 * == null) { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(updatedUser); }
	 */

    @ApiOperation(value = "Update a user by ID", response = User.class)
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "Delete a user by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
    @ApiOperation(value = "Get all roles", response = List.class)
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return userService.getAllRoles();
    }

    @ApiOperation(value = "Get a role by ID", response = Role.class)
    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = userService.getRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @ApiOperation(value = "Create a new role", response = Role.class)
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        userService.createRole(role);
        return ResponseEntity.ok(role);
    }

    @ApiOperation(value = "Update a role by ID", response = Role.class)
    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = userService.updateRole(id, role);
        if (updatedRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRole);
    }

    @ApiOperation(value = "Delete a role by ID")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        userService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get all privileges", response = List.class)
    @GetMapping("/privileges")
    public List<Privilege> getAllPrivileges() {
        return userService.getAllPrivileges();
    }

    @ApiOperation(value = "Get a privilege by ID", response = Privilege.class)
    @GetMapping("/privileges/{id}")
    public ResponseEntity<Privilege> getPrivilegeById(@PathVariable Long id) {
        Privilege privilege = userService.getPrivilegeById(id);
        if (privilege == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(privilege);
    }

    @ApiOperation(value = "Create a new privilege", response = Privilege.class)
    @PostMapping("/privileges")
    public ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilege) {
        userService.createPrivilege(privilege);
        return ResponseEntity.ok(privilege);
    }

    @ApiOperation(value = "Update a privilege by ID", response = Privilege.class)
    @PutMapping("/privileges/{id}")
    public ResponseEntity<Privilege> updatePrivilege(@PathVariable Long id, @RequestBody Privilege privilege) {
        Privilege updatedPrivilege = userService.updatePrivilege(id, privilege);
        if (updatedPrivilege == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPrivilege);
    }

    @ApiOperation(value = "Delete a privilege by ID")
    @DeleteMapping("/privileges/{id}")
    public ResponseEntity<Void> deletePrivilege(@PathVariable Long id) {
        userService.deletePrivilege(id);
        return ResponseEntity.ok().build();
    }
    
	/*
	 * @DeleteMapping("/users/{userId}/roles/{roleId}")
	 * 
	 * @ApiOperation(value = "Remove a role from a user") public
	 * ResponseEntity<Void> removeRoleFromUser(@PathVariable Long
	 * userId, @PathVariable Long roleId) { User user =
	 * userService.removeRoleFromUser(userId, roleId); if (user == null) { return
	 * ResponseEntity.notFound().build(); } return ResponseEntity.ok().build(); }
	 */
    
	/*
	 * @PostMapping("/roles/{roleId}/privilege")
	 * 
	 * @ApiOperation(value = "Add a privilege to a role", response = Role.class)
	 * public ResponseEntity<Role> addPrivilegeToRole(
	 * 
	 * @PathVariable Long roleId,
	 * 
	 * @RequestBody Privilege privilege ) { Role updatedRole =
	 * userService.addPrivilegeToRole(roleId, privilege); if (updatedRole == null) {
	 * return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(updatedRole); }
	 */
    
	/*
	 * @DeleteMapping("/roles/{roleId}/privileges/{privilegeId}")
	 * 
	 * @ApiOperation(value = "Remove a privilege from a role", response =
	 * Role.class) public ResponseEntity<Role> removePrivilegeFromRole(
	 * 
	 * @PathVariable Long roleId,
	 * 
	 * @PathVariable Long privilegeId ) { Role updatedRole =
	 * userService.removePrivilegeFromRole(roleId, privilegeId); if (updatedRole ==
	 * null) { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(updatedRole); }
	 */
    
    @ApiOperation(value = "Add List of roles to a user by user ID", notes = "Adds roles to a user by user ID")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Roles added to user successfully"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/users/{userId}/roles")
    public ResponseEntity<User> addRolesToUser(
            @ApiParam(value = "User ID", required = true) @PathVariable Long userId,
            @ApiParam(value = "List of role IDs", required = true) @RequestBody List<Long> roleIds) {
        User user = userService.addRolesToUser(userId, roleIds);
		return ResponseEntity.ok(user);
    }

	/*
	 * // @ApiOperation(value =
	 * "Add a list of privileges to a role by role ID and privilege IDs")
	 * // @ApiResponses(value = { // @ApiResponse(code = 200, message =
	 * "Privileges added to role successfully"), // @ApiResponse(code = 404, message
	 * = "Role or privileges not found"), // @ApiResponse(code = 500, message =
	 * "Internal server error") // }) // @PostMapping("/roles/{roleId}/privileges")
	 * // public ResponseEntity<Role> addPrivilegesToRole( // @PathVariable Long
	 * roleId, // @RequestBody List<Long> privilegeIds) { // Role
	 * role=userService.addPrivilegesToRole(roleId, privilegeIds); // //
	 * Optional<Role> optionalRole = roleRepository.findById(roleId); // if
	 * (optionalRole.isPresent()) { // Role role = optionalRole.get(); //
	 * List<Privilege> privileges = privilegeRepository.findAllById(privilegeIds);
	 * // if (!privileges.isEmpty()) { // role.getPrivileges().addAll(privileges);
	 * // roleRepository.save(role); // return ResponseEntity.ok(role); // } else {
	 * // throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "Privileges not found"); // } // } else { // throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"); // } // }
	 */    
    /**
     * Adds a list of privileges to a role by role ID and privilege IDs
     *
     * @param roleId        ID of the role to add privileges to
     * @param privilegeIds  IDs of the privileges to add to the role
     * @return              The updated Role object with the added privileges
     */
    @ApiOperation(value = "Add List of privileges to role", notes = "Adds a list of privileges to a role by role ID and privilege IDs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Privileges added to role successfully"),
        @ApiResponse(code = 400, message = "Invalid role or privilege ID(s) provided"),
        @ApiResponse(code = 404, message = "Role or privilege(s) not found")
    })
    @PostMapping("/roles/{roleId}/privileges")
    public Role addPrivilegesToRole(
            @ApiParam(value = "ID of the role to add privileges to", required = true)
            @PathVariable Long roleId,

            @ApiParam(value = "IDs of the privileges to add to the role", required = true)
            @RequestBody List<Long> privilegeIds) {
        return userService.addPrivilegesToRole(roleId, privilegeIds);
    }

    /**
     * Adds a list of privileges to a role by role ID and privilege IDs
     *
     * @param roleId        ID of the role to add privileges to
     * @param privilegeIds  IDs of the privileges to add to the role
     * @return              The updated Role object with the added privileges
     */
    @ApiOperation(value = "Fetch information about Privileges assigned to a role", notes = "return a list of privileges assigned to a role ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Privileges  to role fetched successfully"),
        @ApiResponse(code = 400, message = "Invalid role or privilege ID(s) provided"),
        @ApiResponse(code = 404, message = "Role or privilege(s) not found")
    })
    @GetMapping("/roles/{name}/privileges")
    public ResponseEntity<Set<Privilege>> getPrivilegesForRole(@PathVariable("name") String roleName) {
        Set<Privilege> privileges = userService.getPrivilegesForRole(roleName);
        if (privileges != null) {
            return ResponseEntity.ok(privileges);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Adds a list of privileges to a role by role ID and privilege IDs
     *
     * @param roleId        ID of the role to add privileges to
     * @param privilegeIds  IDs of the privileges to add to the role
     * @return              The updated Role object with the added privileges
     */
    @ApiOperation(value = "Fetch information about Privileges assigned to a role based upon role id", notes = "return a list of privileges assigned to a role ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Privileges  to role fetched successfully"),
        @ApiResponse(code = 400, message = "Invalid role or privilege ID(s) provided"),
        @ApiResponse(code = 404, message = "Role or privilege(s) not found")
    })
    @GetMapping("/{roleId}/privileges")
    public Set<Privilege> getPrivilegesByRoleId(@PathVariable Long roleId) {
        return userService.findPrivilegesByRoleId(roleId);
    }
    
    
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
    	ResponseEntity<UserResponse> userResponse= userService.findByUsernameWithRoles(username);
		return userResponse;
    }

     
}

