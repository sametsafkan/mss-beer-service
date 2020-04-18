package com.sametsafkan.mssbeerservice.web.service;

import com.sametsafkan.mssbeerservice.web.domain.Beer;
import com.sametsafkan.mssbeerservice.web.exception.RecordNotFoundException;
import com.sametsafkan.mssbeerservice.web.mapper.BeerMapper;
import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import com.sametsafkan.mssbeerservice.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto findById(UUID id) {
        return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto update(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(RecordNotFoundException::new);
        beer.setName(beerDto.getName());
        beer.setStyle(beerDto.getStyle().name());
        beer.setUpc(beerDto.getUpc());
        beer.setPrice(beerDto.getPrice());
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public void delete(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(RecordNotFoundException::new);
        beerRepository.delete(beer);
    }
}
