package com.sametsafkan.brewery.event;

import com.sametsafkan.brewery.model.BeerOrderDto;
import lombok.Data;

@Data
public class BeerOrderValidationRequest {
    private BeerOrderDto beerOrderDto;
}
