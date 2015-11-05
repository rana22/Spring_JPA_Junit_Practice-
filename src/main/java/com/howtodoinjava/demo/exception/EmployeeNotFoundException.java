package com.howtodoinjava.demo.exception;


/**
 * @author Ambar Bdr R
 */
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{

	public EmployeeNotFoundException (String message){
		super( message);
	}
}
