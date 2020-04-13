package com.sametsafkan.mssbeerservice.web.repository;

import com.sametsafkan.mssbeerservice.web.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
