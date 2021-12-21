package com.codingtask.snakesandladders.service.impl;

import lombok.AllArgsConstructor;

import com.codingtask.snakesandladders.entity.Player;
import com.codingtask.snakesandladders.exception.EntityNotFoundException;
import com.codingtask.snakesandladders.repository.PlayerRepositoryImpl;
import com.codingtask.snakesandladders.service.PlayerService;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerRepositoryImpl playerRepository;

	@Override
	public Player create() {
		return playerRepository.create();
	}

	@Override
	public Player findById(long id) {
		return playerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Player with id %s not found.", id)));
	}
}
