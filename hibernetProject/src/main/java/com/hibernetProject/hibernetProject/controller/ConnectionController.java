package com.hibernetProject.hibernetProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ConnectionController {

	@GetMapping("/")
	public ResponseEntity<?> getConn(){
		return new ResponseEntity<>("welcome to this project", HttpStatus.OK);
	}
}
