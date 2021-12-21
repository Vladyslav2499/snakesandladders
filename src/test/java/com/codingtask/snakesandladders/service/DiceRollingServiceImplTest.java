package com.codingtask.snakesandladders.service;

import com.codingtask.snakesandladders.service.impl.DiceRollingServiceImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class DiceRollingServiceImplTest {

	private static final int LEFT_BOUND = 1;
	private static final int RIGHT_BOUND = 6;

	private final DiceRollingServiceImpl diceRollingService = new DiceRollingServiceImpl();

	@Test
	public void shouldRollValueFromOneToSixInclusive() {
		int rolledValue = diceRollingService.roll();

		assertTrue(LEFT_BOUND <= rolledValue && RIGHT_BOUND >= rolledValue);
	}
}
