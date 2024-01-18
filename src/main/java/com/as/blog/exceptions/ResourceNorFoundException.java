package com.as.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNorFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fildValue;

	public ResourceNorFoundException(String resourceName, String fieldName, long fildValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fildValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fildValue = fildValue;
	}

}
