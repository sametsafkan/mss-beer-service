package com.sametsafkan.mssbeerservice.event;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
