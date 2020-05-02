package com.sametsafkan.mssbeerservice.service.inventory;

import com.sametsafkan.mssbeerservice.service.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Profile("local-discovery")
@RequiredArgsConstructor
public class BeerInventoryFeignImpl implements BeerInventory{

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.info("Calling inventory service...");
        ResponseEntity<List<BeerInventoryDto>> onhandInventory = inventoryServiceFeignClient.getOnhandInventory(beerId);
        Integer onHand = onhandInventory.getBody().stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        log.info("Called inventory service. Onhand : " + onHand);
        return onHand;
    }
}
