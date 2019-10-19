package com.cluster.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.cluster.jpa.user;
import com.cluster.jpa.userRepository;

@Service("userService")
public class userServiceImp implements userService {
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */
	@Autowired
	userRepository userRepository;
	static Random rand;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";

	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();

	@Override
	public String login(String username, String password) {
		Optional<user> user = userRepository.login(username, password);
		if (user.isPresent()) {
			String token = UUID.randomUUID().toString();
			user custom = user.get();
			custom.setToken(token);
			userRepository.save(custom);
			return token;
		}

		return StringUtils.EMPTY;
	}

	@Override
	public Optional<User> findByToken(String token) {
		Optional<user> user = userRepository.findByToken(token);
		if (user.isPresent()) {
			user user1 = user.get();
			User user2 = new User(user1.getUserName(), user1.getPassword(), true, true, true, true,
					AuthorityUtils.createAuthorityList("USER"));
			return Optional.of(user2);
		}
		return Optional.empty();
	}

	@Override
	public user findById(Long id) {
		Optional<user> user = userRepository.findById(id);
		return user.orElse(null);
	}

	public void CreateUser() {
		user e = new user();
		e.setPassword(generateRandomString(10));
		e.setUserName(generateRandomString(10));
		userRepository.save(e);
		this.login(e.getUserName(), e.getPassword());

	}

	public static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			// debug
			// System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

			sb.append(rndChar);

		}

		return sb.toString();

	}
}
