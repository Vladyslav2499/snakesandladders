package com.codingtask.snakesandladders.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

	private static final long GAME_ID = 1L;
	private static final long PLAYER_ID = 1L;

	private static final int START_TOKEN_POSITION = 1;
	private static final int ROLLED_VALUE_THREE = 3;
	private static final int ROLLED_VALUE_FOUR = 4;
	private static final int TOKEN_POSITION_FOUR = 4;
	private static final int TOKEN_POSITION_EIGHT = 8;

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
		assertEquals(START_TOKEN_POSITION, actual.getPlayer().getTokenPosition());
	}

	@Test
	void shouldMovePlayerTokenFromStartPositionByThreePoints() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_THREE);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_FOUR, actual.getPlayer().getTokenPosition());
		assertEquals(ROLLED_VALUE_THREE, actual.getRolledValue());
	}

	@Test
	void shouldMakeTwoPlayerTurns() {
		doReturn(game).when(testInstance).findById(GAME_ID);
		when(playerService.findById(PLAYER_ID)).thenReturn(player);
		when(diceRollingService.roll()).thenReturn(ROLLED_VALUE_THREE).thenReturn(ROLLED_VALUE_FOUR);

		Game actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_FOUR, actual.getPlayer().getTokenPosition());
		assertEquals(ROLLED_VALUE_THREE, actual.getRolledValue());

		actual = testInstance.moveToken(GAME_ID, PLAYER_ID);

		assertEquals(TOKEN_POSITION_EIGHT, actual.getPlayer().getTokenPosition());
		assertEquals(ROLLED_VALUE_FOUR, actual.getRolledValue());
	}
}
