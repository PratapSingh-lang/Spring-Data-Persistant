/**
 * 
 */
package com.hibernetProject.hibernetProject.service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhanu.pratap
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	 private Long id;
	    private String username;
	    private List<String> roles;
	/**
	 * @param id
	 * @param userName
	 * @param collect
	 */
//	public UserResponse(Long id, String userName, List<String> collect) {
//		// TODO Auto-generated constructor stub
//	}

}
