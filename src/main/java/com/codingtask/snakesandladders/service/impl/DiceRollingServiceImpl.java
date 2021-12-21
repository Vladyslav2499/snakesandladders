package com.codingtask.snakesandladders.service.impl;

import com.codingtask.snakesandladders.service.DiceRollingService;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class DiceRollingServiceImpl implements DiceRollingService {

	private static final Random RANDOM = new Random();

	private static final int LEFT_BOUND = 1;
	private static final int RIGHT_BOUND = 6;


	@Override
	public int roll() {
		return LEFT_BOUND + RANDOM.nextInt(RIGHT_BOUND);
	}
}
