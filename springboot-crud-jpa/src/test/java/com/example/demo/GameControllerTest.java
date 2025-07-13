package com.example.demo;

import com.example.demo.entity.Game;
import com.example.demo.service.GameService;
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

@WebMvcTest(com.example.demo.controller.GameController.class)
public class GameControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private GameService service;

    @Test void fetchGameById() throws Exception {
        Game game = new Game();
        game.setGameId(1L);
        game.setGameName("Chess");
        game.setGenre("Strategy");
        when(service.getGameById(1L)).thenReturn(game);

        mockMvc.perform(get("/games/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.gameName").value("Chess"));
    }

    @Test void createNewGame() throws Exception {
        when(service.createGame(any(Game.class))).thenReturn(new Game());
        mockMvc.perform(post("/games")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"gameId\":2,\"gameName\":\"Soccer\",\"genre\":\"Sports\"}"))
               .andExpect(status().isOk());
    }

    @Test void fetchAllGames() throws Exception {
        when(service.getAllGames()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/games"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }
}