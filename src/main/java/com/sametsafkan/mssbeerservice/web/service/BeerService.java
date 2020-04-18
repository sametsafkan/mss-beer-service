package com.sametsafkan.mssbeerservice.web.service;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    BeerDto findById(UUID id);

    BeerDto save(BeerDto beerDto);

    BeerDto update(UUID beerId, BeerDto beerDto);

    void delete(UUID beerId);
}
