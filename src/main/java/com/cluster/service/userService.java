package com.cluster.service;

import com.cluster.jpa.user;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface userService {

	String login(String username, String password);

	Optional<User> findByToken(String token);

	user findById(Long id);

	public void CreateUser();
}
