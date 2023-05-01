package com.hibernetProject.hibernetProject.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(String.format(
        		"%s not found with %s : '%s'", entityName, fieldName, fieldValue
//        		"hyibuu"
        		));
    }
}

