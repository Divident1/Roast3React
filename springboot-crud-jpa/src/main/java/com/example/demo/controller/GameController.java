package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.service.GameService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService service;
    public GameController(GameService service) { this.service = service; }

    @GetMapping("/{id}")
    public Game getById(@PathVariable Long id) { 
        return service.getGameById(id);
     }

    @PostMapping
    public Game create(@RequestBody Game game) { 
        return service.createGame(game);
     }

    @GetMapping
    public List<Game> getAll() { 
        return service.getAllGames();
     }
}