package com.sametsafkan.mssbeerservice.web.controller;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import com.sametsafkan.mssbeerservice.web.service.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> findById(@PathVariable("beerId") UUID id){
        return new ResponseEntity<>(new BeerDto().builder().build(), OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BeerDto beerDto){
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto){

    }

    @DeleteMapping("/{beerId}")
    public void delete(@PathVariable("beerId") UUID beerId){

    }
}
