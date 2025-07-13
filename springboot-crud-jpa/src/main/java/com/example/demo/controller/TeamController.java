package com.example.demo.controller;

import com.example.demo.entity.Team;
import com.example.demo.service.TeamService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService service;
    public TeamController(TeamService service) { this.service = service; }

    @GetMapping("/{id}")
    public Team getById(@PathVariable Long id) { 
        return this.service.getTeamById(id);
     }

    @PostMapping
    public Team create(@RequestBody Team team) {
        return this.service.createTeam(team);
     }

    @GetMapping
    public List<Team> getAll() { 
        return this.service.getAllTeams();
     }
}