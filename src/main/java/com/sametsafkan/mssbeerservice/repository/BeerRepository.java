package com.sametsafkan.mssbeerservice.repository;

import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByNameAndStyle(String name, BeerStyle style, PageRequest pageRequest);

    Page<Beer> findAllByName(String name, PageRequest pageRequest);

    Page<Beer> findAllByStyle(BeerStyle style, PageRequest pageRequest);
}
