package com.example.map_hsg6.Exceptions;

/**
 * this class implements the exception for Students that want to register to a course,to whom they already enrolled
 */
public class StudentAlreadyEnrolled extends  Exception{
    public StudentAlreadyEnrolled(String message) {
        super(message);
    }
}
