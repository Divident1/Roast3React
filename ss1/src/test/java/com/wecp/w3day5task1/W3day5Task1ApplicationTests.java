package com.wecp.w3day5task1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class W3day5Task1ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAuthenticationRequired() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void testUserShouldAccessToUserEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Welcome"));
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void testUserShouldNotAccessToAdminEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "ADMIN")
	public void testAdminShouldAccessToUserEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Welcome"));
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "ADMIN")
	public void testAdminShouldAccessToAdminEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Welcome Admin"));
	}

}
