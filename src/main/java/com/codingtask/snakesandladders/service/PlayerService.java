package com.codingtask.snakesandladders.service;

import com.codingtask.snakesandladders.entity.Player;


public interface PlayerService {

	Player create();

	Player findById(long id);
}
