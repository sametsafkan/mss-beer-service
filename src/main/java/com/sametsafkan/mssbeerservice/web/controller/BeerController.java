package com.sametsafkan.mssbeerservice.web.controller;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import com.sametsafkan.mssbeerservice.service.BeerService;
import com.sametsafkan.mssbeerservice.web.model.BeerPagedList;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final static int DEFAULT_PAGE_NUMBER = 0;
    private final static int DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = {"beerListCache"}, condition = "#showInventoryOnHand == false ")
    public BeerPagedList listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyle beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand",required = false, defaultValue = "false") Boolean showInventoryOnHand){
        if(pageNumber == null || pageNumber < 0)
            pageNumber = DEFAULT_PAGE_NUMBER;
        if(pageSize == null || pageSize < 0)
            pageSize = DEFAULT_PAGE_SIZE;
        BeerPagedList beerPagedList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),
                showInventoryOnHand);
        return beerPagedList;
    }

    @GetMapping("/{beerId}/{showInventoryOnHand}")
    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    public BeerDto findById(@PathVariable("beerId") UUID id,@RequestParam(value = "showInventoryOnHand", defaultValue = "false", required = false) Boolean showInventoryOnHand){
        if(showInventoryOnHand == null)
            showInventoryOnHand = false;
        return beerService.findById(id, showInventoryOnHand);
    }

    @GetMapping("/findByUpc/{upc}")
    @Cacheable(cacheNames = "beerCache", key = "#upc", condition = "#showInventoryOnHand == false ")
    public BeerDto findByUpc(@PathVariable("upc") String upc, @RequestParam(value = "showInventoryOnHand", defaultValue = "false",required = false) Boolean showInventoryOnHand){
        if(showInventoryOnHand == null)
            showInventoryOnHand = false;
        return beerService.findByUpc(upc, showInventoryOnHand);
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody BeerDto beerDto){
        BeerDto savedBeer = beerService.save(beerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity(headers, CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
        beerService.update(beerId, beerDto);
    }

    @DeleteMapping("/{beerId}")
    public void delete(@PathVariable("beerId") UUID beerId){
        beerService.delete(beerId);
    }
}
