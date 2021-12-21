package com.codingtask.snakesandladders.repository;

import com.codingtask.snakesandladders.entity.Player;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PlayerRepositoryImpl {

	private static final List<Player> PLAYER_LIST = new ArrayList<>();

	public Player create() {
		Player newPlayer = new Player();
		PLAYER_LIST.add(newPlayer);
		return newPlayer;
	}

	public Optional<Player> findById(long playerId) {
		return PLAYER_LIST.stream()
				.filter(player -> player.getId() == playerId)
				.findFirst();
	}
}
