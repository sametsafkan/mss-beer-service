package com.sametsafkan.mssbeerservice.web.controller;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import com.sametsafkan.mssbeerservice.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> findById(@PathVariable("beerId") UUID id){
        return new ResponseEntity<>(beerService.findById(id), OK);
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
