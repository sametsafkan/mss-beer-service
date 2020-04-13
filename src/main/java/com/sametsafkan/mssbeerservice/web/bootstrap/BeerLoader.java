package com.sametsafkan.mssbeerservice.web.bootstrap;

import com.sametsafkan.mssbeerservice.web.domain.Beer;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import com.sametsafkan.mssbeerservice.web.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeers();
    }

    private void loadBeers() {
        beerRepository.save(new Beer().builder()
                .name("Tuborg")
                .style(BeerStyle.PALE_ALE.toString())
                .minOnHand(10)
                .quantityToBrew(150)
                .price(new BigDecimal(15.95))
                .upc(1234561231L)
                .build());
        beerRepository.save(new Beer().builder()
                .name("Efes")
                .style(BeerStyle.ALE.toString())
                .minOnHand(8)
                .quantityToBrew(100)
                .price(new BigDecimal(15.95))
                .upc(1212312L)
                .build());
        System.out.println(beerRepository.count());
    }
}
