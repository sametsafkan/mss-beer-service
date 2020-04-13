package com.sametsafkan.mssbeerservice.web.controller;

import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity save(@Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
    }

    @DeleteMapping("/{beerId}")
    public void delete(@PathVariable("beerId") UUID beerId){

    }
}
