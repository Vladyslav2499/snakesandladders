package com.codingtask.snakesandladders.repository;

import com.codingtask.snakesandladders.entity.Game;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class GameRepositoryImpl {

	private static final List<Game> GAME_LIST = new ArrayList<>();

	public Game create() {
		Game newGame = new Game();
		GAME_LIST.add(newGame);
		return newGame;
	}

	public Optional<Game> findById(long id) {
		return GAME_LIST.stream()
				.filter(game -> game.getId() == id)
				.findFirst();
	}
}
