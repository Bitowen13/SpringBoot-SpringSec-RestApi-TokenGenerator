package com.cluster.controller;

import com.cluster.service.userService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

	@Autowired
	private userService userService;

	@RequestMapping(value = { "/token" }, method = RequestMethod.POST)

	public ResponseEntity<?> getToken(@RequestParam("username") final String username,
			@RequestParam("password") final String password) {
		String token = userService.login(username, password);
		if (StringUtils.isEmpty(token)) {
			return new ResponseEntity<String>("no token found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
}
