package com.sametsafkan.mssbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import com.sametsafkan.mssbeerservice.web.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BeerService beerService;

    @Test
    void findById() throws Exception {
        given(beerService.findById(any())).willReturn(getValidBeerDto());
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        BeerDto beer = getValidBeerDto();
        String beerToJson = mapper.writeValueAsString(beer);
        given(beerService.save(any())).willReturn(getValidBeerForSaveDto());
        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON).content(beerToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception{
        BeerDto beer = getValidBeerDto();
        String beerToJson = mapper.writeValueAsString(beer);
        given(beerService.update(any(), any())).willReturn(getValidBeerDto());
        mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON).content(beerToJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto(){
        return new BeerDto().builder()
                .name("Tuborg")
                .style(BeerStyle.ALE)
                .price(new BigDecimal(10.95))
                .upc("0631234200036")
                .build();
    }

    BeerDto getValidBeerForSaveDto(){
        return new BeerDto().builder()
                .id(UUID.randomUUID())
                .name("Tuborg")
                .style(BeerStyle.ALE)
                .price(new BigDecimal(10.95))
                .upc("0631234200036")
                .build();
    }
}