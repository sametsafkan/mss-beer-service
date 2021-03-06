package com.sametsafkan.mssbeerservice.repository;

import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.brewery.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByNameAndStyle(String name, BeerStyle style, PageRequest pageRequest);

    Page<Beer> findAllByName(String name, PageRequest pageRequest);

    Page<Beer> findAllByStyle(BeerStyle style, PageRequest pageRequest);

    Beer findByUpc(String upc);
}
