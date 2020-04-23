package com.sametsafkan.mssbeerservice.event;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
