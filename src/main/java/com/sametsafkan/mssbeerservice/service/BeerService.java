package com.sametsafkan.mssbeerservice.service;

import com.sametsafkan.brewery.model.BeerDto;
import com.sametsafkan.brewery.model.BeerPagedList;
import com.sametsafkan.brewery.model.BeerStyle;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto findById(UUID id, Boolean showInventoryOnHand);

    BeerDto save(BeerDto beerDto);

    BeerDto update(UUID beerId, BeerDto beerDto);

    void delete(UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto findByUpc(String upc, Boolean showInventoryOnHand);
}
