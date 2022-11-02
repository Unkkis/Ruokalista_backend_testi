package com.example.RuokalistaApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class RestApiTests {
	
	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void checkApiStatus() throws Exception {
		this.mockMvc.perform(get("/api"));
	}
	
	@Test
	public void checkResponseTypeJSON () throws Exception {
		this.mockMvc.perform(get("/api/all")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	

}
