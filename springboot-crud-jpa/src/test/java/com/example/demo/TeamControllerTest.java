package com.example.demo;

import com.example.demo.entity.Team;
import com.example.demo.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(com.example.demo.controller.TeamController.class)
public class TeamControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private TeamService service;

    @Test void fetchTeamById() throws Exception {
        Team team = new Team();
        team.setTeamId(1L);
        team.setTeamName("Alpha");
        team.setPlayers(Collections.emptyList());
        when(service.getTeamById(1L)).thenReturn(team);

        mockMvc.perform(get("/teams/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.teamName").value("Alpha"));
    }

    @Test void createNewTeam() throws Exception {
        when(service.createTeam(any(Team.class))).thenReturn(new Team());
        mockMvc.perform(post("/teams")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"teamId\":2,\"teamName\":\"Beta\",\"players\":[\"P1\",\"P2\"]}"))
               .andExpect(status().isOk());
    }

    @Test void fetchAllTeams() throws Exception {
        when(service.getAllTeams()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/teams"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }
}