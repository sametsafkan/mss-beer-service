package com.sametsafkan.mssbeerservice.service.inventory;

import com.sametsafkan.mssbeerservice.service.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@ConfigurationProperties(prefix = "com.sametsafkan.inventory", ignoreUnknownFields = false)
public class BeerInventoryImpl implements BeerInventory {

    private final RestTemplate restTemplate;
    private String beerInventoryHost;
    private String  beerInventoryPath;

    public BeerInventoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBeerInventoryPath(String beerInventoryPath) {
        this.beerInventoryPath = beerInventoryPath;
    }

    public void setBeerInventoryHost(String beerInventoryHost) {
        this.beerInventoryHost = beerInventoryHost;
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.info("Calling inventory service...");
        List<BeerInventoryDto> a;
        ResponseEntity<List<BeerInventoryDto>> responseEntity =
                restTemplate.exchange(beerInventoryHost + beerInventoryPath, GET,null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, beerId);
        return responseEntity.getBody().stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
