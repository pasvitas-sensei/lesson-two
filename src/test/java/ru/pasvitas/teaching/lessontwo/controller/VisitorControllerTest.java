package ru.pasvitas.teaching.lessontwo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pasvitas.teaching.lessontwo.model.Visitor;
import ru.pasvitas.teaching.lessontwo.repository.VisitorRepository;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VisitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorRepository visitorRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Register visitor")
    @Test
    public void registerVisitor() throws Exception {
        Visitor inputVisitor = new Visitor(null, "Name", "Surname", 0, null);
        Visitor expectedVisitor = new Visitor(1L, "Name", "Surname", 0, null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/visitors")
                        .content(objectMapper.writeValueAsString(inputVisitor))
                        .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedVisitor)));
        assertEquals(expectedVisitor, visitorRepository.findById(1L).get());
    }

    @DisplayName("Get visitors")
    @Test
    void getVisitors() throws Exception {
        Visitor savedVisitor = new Visitor(1L, "Name", "Surname", 0, null);
        visitorRepository.save(savedVisitor);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/visitors")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(List.of(savedVisitor))));
    }

    @Test
    void getVisitor() {
    }
}