package com.sametsafkan.mssbeerservice.event;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 7218595810658668115L;

    private final BeerDto beerDto;
}
