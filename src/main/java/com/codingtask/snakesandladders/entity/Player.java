package com.codingtask.snakesandladders.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Player {

	private static long UNIQUE_ID = 1L;

	private long id;

	private int tokenPosition;

	public Player() {
		id = UNIQUE_ID++;
		tokenPosition = 1;
	}
}
