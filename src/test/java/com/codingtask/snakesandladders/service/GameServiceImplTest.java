package com.codingtask.snakesandladders.service;

import com.codingtask.snakesandladders.entity.Game;
import com.codingtask.snakesandladders.entity.Player;
import com.codingtask.snakesandladders.repository.GameRepositoryImpl;
import com.codingtask.snakesandladders.service.impl.GameServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

	private static final long GAME_ID = 1L;
	private static final long PLAYER_ID = 1L;

	private static final int START_TOKEN_POSITION = 1;
	private static final int ROLLED_VALUE_THREE = 3;
	private static final int ROLLED_VALUE_FOUR = 4;
	private static final int TOKEN_POSITION_FOUR = 4;
	private static final int TOKEN_POSITION_FIVE = 5;
	private static final int TOKEN_POSITION_EIGHT = 8;
	private static final int TOKEN_POSITION_NINE = 9;
	private static final int WINNER_TOKEN_POSITION = 100;
	private static final int BEFORE_LAST_STEP_TOKEN_POSITION = 97;

	@Spy
	@InjectMocks
	private GameServiceImpl testInstance;

	@Mock
	private GameRepositoryImpl gameRepository;

	@Mock
	private PlayerService playerService;

	@Mock
	private DiceRollingService diceRollingService;

	private Player player;
	private Game game;

	@BeforeEach
	void setUp() {
		player = new Player();
		player.setId(PLAYER_ID);

		game = new Game();
		game.setId(GAME_ID);
	}

	@Test
	void shouldPlaceTokenOnTheStartPositionWhenGameCreated() {
		when(playerService.create()).thenReturn(player);
		when(gameRepository.create()).thenReturn(game);

		Game actual = testInstance.create();

		assertNotNull(actual.getPlayer());
		assertFalse(actual.getPlayer().isWinner());
		assertEquals(START_TOKEN_POSITION, actual.getPlayer().getTokenPosition());
	}

	@Test
	void shouldMovePlayerTokenFromStartPositionByThreePoints() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_THREE);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_FOUR, actual.getPlayer().getTokenPosition());
		assertFalse(actual.getPlayer().isWinner());
		assertEquals(ROLLED_VALUE_THREE, actual.getRolledValue());
	}

	@Test
	void shouldMakeTwoPlayerTurns() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_THREE).thenReturn(ROLLED_VALUE_FOUR);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_FOUR, actual.getPlayer().getTokenPosition());
		assertFalse(actual.getPlayer().isWinner());
		assertEquals(ROLLED_VALUE_THREE, actual.getRolledValue());

		actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_EIGHT, actual.getPlayer().getTokenPosition());
		assertFalse(actual.getPlayer().isWinner());
		assertEquals(ROLLED_VALUE_FOUR, actual.getRolledValue());
	}

	@Test
	void shouldMovePlayerTokenByGivenFourPoints() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_FOUR);
		player.setTokenPosition(TOKEN_POSITION_FIVE);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_NINE, actual.getPlayer().getTokenPosition());
		assertFalse(actual.getPlayer().isWinner());
		assertEquals(ROLLED_VALUE_FOUR, actual.getRolledValue());
	}

	@Test
	void shouldDefinePlayerAsWinner() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_THREE);
		player.setTokenPosition(BEFORE_LAST_STEP_TOKEN_POSITION);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(WINNER_TOKEN_POSITION, actual.getPlayer().getTokenPosition());
		assertEquals(ROLLED_VALUE_THREE, actual.getRolledValue());
		assertTrue(actual.getPlayer().isWinner());
	}

	@Test
	void shouldNotDefinePlayerAsWinner() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_FOUR);
		player.setTokenPosition(BEFORE_LAST_STEP_TOKEN_POSITION);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(BEFORE_LAST_STEP_TOKEN_POSITION, actual.getPlayer().getTokenPosition());
		assertEquals(ROLLED_VALUE_FOUR, actual.getRolledValue());
		assertFalse(actual.getPlayer().isWinner());
	}
}
