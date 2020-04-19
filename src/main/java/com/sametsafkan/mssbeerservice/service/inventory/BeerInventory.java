package com.sametsafkan.mssbeerservice.service.inventory;

import java.util.UUID;

public interface BeerInventory {

    Integer getOnHandInventory(UUID beerId);
}
