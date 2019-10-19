package com.cluster.service;

import java.util.List;

import javax.transaction.Transactional;

import com.cluster.jpa.idea;

public interface ideaInterface {
	@Transactional
	idea Saveidea(idea idea);

	@Transactional
	idea Saveidea(String name, Long duration);

	@Transactional
	void Deleteidea(Long id) throws Exception;

	@Transactional
	idea GetOneidea(Long id) throws Exception;

	@Transactional
	List<idea> GetAll() throws Exception;

	@Transactional
	public void checkideas();

}
