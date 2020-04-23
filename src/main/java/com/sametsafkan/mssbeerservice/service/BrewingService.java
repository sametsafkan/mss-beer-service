package com.sametsafkan.mssbeerservice.service;

import com.sametsafkan.mssbeerservice.config.JmsConfig;
import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.mssbeerservice.event.BrewBeerEvent;
import com.sametsafkan.mssbeerservice.repository.BeerRepository;
import com.sametsafkan.mssbeerservice.service.inventory.BeerInventory;
import com.sametsafkan.mssbeerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventory beerInventory;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(this::checkForLowInventory);
    }

    private void checkForLowInventory(Beer beer) {
        Integer invQOH = beerInventory.getOnHandInventory(beer.getId());
        log.debug("Min OnHand is : " + beer.getMinOnHand());
        log.debug("Inventory is : " + invQOH);
        if(beer.getMinOnHand() >= invQOH){
            jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent((beerMapper.beerToBeerDto(beer))));
        }
    }
}
