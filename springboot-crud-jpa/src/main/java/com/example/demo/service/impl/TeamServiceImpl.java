package com.example.demo.service.impl;

import com.example.demo.entity.Team;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.TeamService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repo;
    public TeamServiceImpl(TeamRepository repo) { this.repo = repo; }

    @Override
    public Team getTeamById(Long id) {
        return repo.findById(id).orElse(null);
     }

    @Override
    public Team createTeam(Team team) { 
        return repo.save(team);
     }

    @Override
    public List<Team> getAllTeams() { 
        return repo.findAll();
     }
}