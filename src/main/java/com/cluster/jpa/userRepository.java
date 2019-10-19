package com.cluster.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends CrudRepository<user, Long> {

	@Query(value = "SELECT u FROM user u where u.userName = ?1 and u.password = ?2 ")
	Optional<user> login(String username, String password);

	Optional<user> findByToken(String token);
}
