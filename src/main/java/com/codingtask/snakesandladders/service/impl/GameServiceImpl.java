package com.codingtask.snakesandladders.service.impl;

import lombok.AllArgsConstructor;

import com.codingtask.snakesandladders.entity.Game;
import com.codingtask.snakesandladders.entity.Player;
import com.codingtask.snakesandladders.exception.EntityNotFoundException;
import com.codingtask.snakesandladders.repository.GameRepositoryImpl;
import com.codingtask.snakesandladders.service.DiceRollingService;
import com.codingtask.snakesandladders.service.GameService;
import com.codingtask.snakesandladders.service.PlayerService;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

	private final GameRepositoryImpl gameRepository;

	private final PlayerService playerService;

	private final DiceRollingService diceRollingService;

	@Override
	public Game create() {
		Game newGame = gameRepository.create();
		Player newPlayer = playerService.create();
		newGame.setPlayer(newPlayer);

		return newGame;
	}

	@Override
	public Game findById(long id) {
		return gameRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s not found.", id)));
	}

	@Override
	public Game moveToken(long gameId, long playerId) {
		Game game = findById(gameId);
		Player player = playerService.findById(playerId);
		int rolledValue = diceRollingService.roll();

		player.setTokenPosition(computeTokenPosition(player.getTokenPosition(), rolledValue));
		game.setRolledValue(rolledValue);
		game.setPlayer(player);

		return game;
	}

	private int computeTokenPosition(int currentTokenPosition, int rolledValue) {
		return currentTokenPosition + rolledValue;
	}
}
