package com.cluster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cluster.jpa.idea;
import com.cluster.service.ideaInterface;

@Component
@RestController
@RequestMapping("idea")
public class ideaController {
	int n = 0;
	@Autowired
	private ideaInterface ideaInterface;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> GetIdea(@PathVariable Long id) throws Exception {
		return new ResponseEntity<idea>(ideaInterface.GetOneidea(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/getallexpsort", method = RequestMethod.GET)
	public ResponseEntity<?> GetallExpSort() throws Exception {
		List<idea> findAll = ideaInterface.GetAll();
		int n = findAll.size();

		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)

				if (findAll.get(j).getExpiredate().after(findAll.get(j + 1).getExpiredate())) {

					idea temp = findAll.get(j);
					findAll.set(j, findAll.get(j + 1));
					findAll.set(j + 1, temp);

				}

		return new ResponseEntity<List<idea>>(findAll, HttpStatus.OK);
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<?> GetAll() throws Exception {
		List<idea> findAll = ideaInterface.GetAll();

		return new ResponseEntity<List<idea>>(findAll, HttpStatus.OK);
	}

	@RequestMapping(value = "/addidea", method = RequestMethod.POST)
	public ResponseEntity<?> AddIdea(@RequestParam String name, @RequestParam Long duration) {
		ideaInterface.Saveidea(name, duration);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Scheduled(cron = "* * * * * *")
	public void check() {
		ideaInterface.checkideas();
	}

}
