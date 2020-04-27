package com.sametsafkan.mssbeerservice.service;

import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.mssbeerservice.exception.RecordNotFoundException;
import com.sametsafkan.mssbeerservice.web.mapper.BeerMapper;
import com.sametsafkan.brewery.model.BeerDto;
import com.sametsafkan.mssbeerservice.repository.BeerRepository;
import com.sametsafkan.brewery.model.BeerPagedList;
import com.sametsafkan.brewery.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerPagedList listBeers(String name, BeerStyle style, PageRequest pageRequest, Boolean showInventoryOnHand) {
        Page<Beer> page;
        if(!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style))
            page = beerRepository.findAllByNameAndStyle(name, style, pageRequest);
        else if(!StringUtils.isEmpty(name) && StringUtils.isEmpty(style))
            page = beerRepository.findAllByName(name, pageRequest);
        else if(StringUtils.isEmpty(name) && !StringUtils.isEmpty(style))
            page = beerRepository.findAllByStyle(style, pageRequest);
        else
            page = beerRepository.findAll(pageRequest);
        return new BeerPagedList(page.getContent().stream()
                                    .map(beer -> showInventoryOnHand.booleanValue() ? beerMapper.beerToBeerDtoWithInventory(beer) : beerMapper.beerToBeerDto(beer))
                                    .collect(Collectors.toList()),
                                PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize()),
                                page.getTotalElements());
    }

    @Override
    public BeerDto findById(UUID id, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        return showInventoryOnHand.booleanValue()? beerMapper.beerToBeerDtoWithInventory(beer) : beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto findByUpc(String upc, Boolean showInventoryOnHand) {
        Beer beer = beerRepository.findByUpc(upc);
        return showInventoryOnHand.booleanValue() ?
                beerMapper.beerToBeerDtoWithInventory(beer) :
                beerMapper.beerToBeerDto(beer);
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
