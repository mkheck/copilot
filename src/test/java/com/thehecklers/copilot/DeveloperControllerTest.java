package com.thehecklers.copilot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperRepository repository;

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello, Copilot!"));
    }

    @Test
    public void testAllDevs() throws Exception {
        when(repository.findAll()).thenReturn(List.of(
                new Developer(UUID.randomUUID().toString(), "Julien Dubois"),
                new Developer(UUID.randomUUID().toString(), "Mark Heckler"),
                new Developer(UUID.randomUUID().toString(), "Dave Syer"),
                new Developer(UUID.randomUUID().toString(), "Josh Long")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/devs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Julien Dubois"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Mark Heckler"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Dave Syer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("Josh Long"));
    }
}