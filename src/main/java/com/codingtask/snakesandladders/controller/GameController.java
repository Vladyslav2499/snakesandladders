package com.codingtask.snakesandladders.controller;

import lombok.AllArgsConstructor;

import com.codingtask.snakesandladders.entity.Game;
import com.codingtask.snakesandladders.service.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/snakes-and-ladders")
@AllArgsConstructor
public class GameController {

	private final GameService gameService;

	@PostMapping(value = "/games", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> game() {
		Game newGame = gameService.create();
		return new ResponseEntity<>(newGame, HttpStatus.CREATED);
	}

	@PutMapping(value = "/games/{gameId}/player/{playerId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> game(@PathVariable long gameId, @PathVariable long playerId) {
		Game game = gameService.moveToken(gameId, playerId);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}

}
