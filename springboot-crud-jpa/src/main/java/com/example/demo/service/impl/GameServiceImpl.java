package com.example.demo.service.impl;

import com.example.demo.entity.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repo;
    public GameServiceImpl(GameRepository repo) {
         this.repo = repo;
         }

    @Override
    public Game getGameById(Long id) { 
        
        return repo.findById(id).orElseThrow(null);
    }
       

    @Override
    public Game createGame(Game game) {
        return repo.save(game);
     }

    @Override
    public List<Game> getAllGames() {
        return repo.findAll();
     }
}