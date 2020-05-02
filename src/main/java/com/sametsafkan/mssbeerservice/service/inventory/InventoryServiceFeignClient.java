package com.sametsafkan.mssbeerservice.service.inventory;

import com.sametsafkan.mssbeerservice.service.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


@FeignClient(name = "beer-inventory-service")
public interface InventoryServiceFeignClient {

    @GetMapping(value = "${com.sametsafkan.inventory.beerInventoryPath}")
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);
}
