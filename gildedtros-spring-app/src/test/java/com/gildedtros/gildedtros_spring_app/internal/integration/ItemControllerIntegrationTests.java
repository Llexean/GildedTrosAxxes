package com.gildedtros.gildedtros_spring_app.internal.integration;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ItemControllerIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void createAndUpdateItem() throws Exception {
        ItemRequest request = new ItemRequest("Valid Item Name", 5, 50, ItemType.COMMON);

        mockMvc.perform(post("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.sellIn").value(5))
                .andExpect(jsonPath("$.quality").value(50))
                .andExpect(jsonPath("$.itemType").value(ItemType.COMMON.toString()));

        mockMvc.perform(put(""))
                .andExpect(status().isOk());

        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name=='Valid Item Name')]").exists())
                .andExpect(jsonPath("$[?(@.name=='Valid Item Name')].name").value(request.name()))
                .andExpect(jsonPath("$[?(@.name=='Valid Item Name')].sellIn").value(request.sellIn() - 1))
                .andExpect(jsonPath("$[?(@.name=='Valid Item Name')].quality").value(request.quality() - 1))
                .andExpect(jsonPath("$[?(@.name=='Valid Item Name')].itemType").value(ItemType.COMMON.toString()));
    }
}
