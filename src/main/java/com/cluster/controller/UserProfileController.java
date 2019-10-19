package com.cluster.controller;

import com.cluster.jpa.user;
import com.cluster.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/users")
public class UserProfileController {

	@Autowired
	private userService userService;

	@GetMapping(value = "/api/users/user/{id}", produces = "application/json")
	public user getUserDetail(@PathVariable Long id) {
		return userService.findById(id);
	}

	@Scheduled(cron = "* * * * * *")
	public void CreateUser() {
		userService.CreateUser();
	}
}
