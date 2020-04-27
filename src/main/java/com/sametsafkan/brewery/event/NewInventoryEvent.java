package com.sametsafkan.brewery.event;

import com.sametsafkan.brewery.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
