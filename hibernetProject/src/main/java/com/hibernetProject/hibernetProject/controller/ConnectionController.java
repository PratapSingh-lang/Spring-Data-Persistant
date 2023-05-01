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
	
	@GetMapping("/checkChnahges/p2/ty/tx/ui")
	public ResponseEntity<?> checkChanges(){
		return new ResponseEntity<>("Done some changes in Project", HttpStatus.OK);
	}
	
	@GetMapping("/checkChnahges/p2/ty/tx/ui/rjkec/hvv/jhbj/cngcn/gnfgn/vngn/vnf/mh.vnn")
	public ResponseEntity<?> checkChangesq(){
		return new ResponseEntity<>("Done new   Project", HttpStatus.OK);
	}
}

