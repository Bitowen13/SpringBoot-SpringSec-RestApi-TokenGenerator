package com.cluster.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cluster.jpa.idea;
import com.cluster.jpa.ideaRepository;

@SuppressWarnings("deprecation")
@Service
public class ideaImplement implements ideaInterface {

	@Autowired
	private ideaRepository ideaRepository;
	@Autowired
	private ideaInterface ideaInterface;
	static Random rand;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";

	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();

	@Override
	public idea Saveidea(String name, Long duration) {
		idea i = new idea();
		Date date = new Date();
		Date date2 = new Date(date.getYear(), date.getMonth(), date.getDate() + duration.intValue(), 0, 0, 0);
		Date date3 = new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);

		i.setCreatedate(date);
		i.setName(name);
		i.setDuration(duration);

		i.setBegindate(date3);
		i.setExpiredate(date2);
		i.setLastupdate(null);
		i.setOnsite(false);

		return ideaRepository.save(i);
	}

	@Override
	public void Deleteidea(Long id) throws Exception {
		if (ideaRepository.findById(id) == null)
			throw new Exception("404");
		ideaRepository.deleteById(id);
	}

	@Override
	public idea GetOneidea(Long id) throws Exception {
		if (ideaRepository.findById(id) == null)
			throw new Exception("404");
		return ideaRepository.getOne(id);
	}

	@Override
	public List<idea> GetAll() {
		return ideaRepository.findAll();
	}

	@SuppressWarnings("unused")
	@Override
	public void checkideas() {
		List<idea> findAll = ideaRepository.findAll();
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNow = formatter.format(new Date());
		for (idea x : findAll) {
			String dateexp = formatter.format(x.getExpiredate());
			if (dateexp.equalsIgnoreCase(dateNow)) {
				x.setOnsite(true);
				x.setLastupdate(new Date());
				ideaRepository.save(x);
				System.out.println("go in here");
			}
		}
		rand = new Random();
		int randInt = rand.nextInt(1000) + 1;
		ideaInterface.Saveidea(generateRandomString(20), Long.valueOf(randInt));
	}

	@Override
	public idea Saveidea(idea idea) {
		return ideaRepository.save(idea);

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
