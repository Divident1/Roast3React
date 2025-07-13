package com.example.demo.service;

import com.example.demo.entity.Team;
import java.util.List;

public interface TeamService {
    Team getTeamById(Long id);
    Team createTeam(Team team);
    List<Team> getAllTeams();
}