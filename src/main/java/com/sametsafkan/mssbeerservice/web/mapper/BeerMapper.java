package com.sametsafkan.mssbeerservice.web.mapper;

import com.sametsafkan.mssbeerservice.web.domain.Beer;
import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);
}
