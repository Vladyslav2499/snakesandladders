package com.codingtask.snakesandladders.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game {

	private static long UNIQUE_ID = 1;

	private long id;

	private Player player;

	private int rolledValue;

	public Game() {
		id = UNIQUE_ID++;
	}
}
