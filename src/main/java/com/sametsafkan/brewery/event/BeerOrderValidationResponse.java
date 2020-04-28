package com.sametsafkan.brewery.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderValidationResponse {
    private UUID beerOrderId;
    private boolean isValid;
}
