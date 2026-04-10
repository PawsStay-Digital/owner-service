package com.pawsstay.owner_service.controller;

import com.pawsstay.owner_service.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private OwnerService ownerService;

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/v1/owners/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Owner Service is running on Virtual Threads!"));
    }
}
