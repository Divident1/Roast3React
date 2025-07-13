package com.example.demo.service;

import com.example.demo.entity.Game;
import java.util.List;

public interface GameService {
    Game getGameById(Long id);
    Game createGame(Game game);
    List<Game> getAllGames();
}