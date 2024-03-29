package com.ssingh.redisqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssingh.redisqueue.intfc.MessageGateway;
import com.ssingh.redisqueue.model.Student;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private MessageGateway messageGateway;
	@PostMapping
	public void sendStudentInformation(@RequestBody Student student) {
		messageGateway.sendMessage(student);
		
	}

}
