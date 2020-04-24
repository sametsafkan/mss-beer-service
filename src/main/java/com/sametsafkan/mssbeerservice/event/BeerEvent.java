package com.sametsafkan.mssbeerservice.event;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 7218595810658668115L;

    private BeerDto beerDto;
}
