package com.codingtask.snakesandladders.service;

import com.codingtask.snakesandladders.entity.Game;


public interface GameService {

	Game create();

	Game findById(long id);

	Game moveToken(long gameId, long playerId);
}
