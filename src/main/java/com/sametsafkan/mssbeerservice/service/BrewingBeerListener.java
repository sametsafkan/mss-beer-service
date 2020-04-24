package com.sametsafkan.mssbeerservice.service;

import com.sametsafkan.mssbeerservice.config.JmsConfig;
import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.mssbeerservice.event.BrewBeerEvent;
import com.sametsafkan.mssbeerservice.event.NewInventoryEvent;
import com.sametsafkan.mssbeerservice.repository.BeerRepository;
import com.sametsafkan.mssbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrewingBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event){
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE ,newInventoryEvent);
    }
}
