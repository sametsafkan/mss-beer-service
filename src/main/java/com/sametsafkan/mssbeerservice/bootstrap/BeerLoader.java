package com.sametsafkan.mssbeerservice.bootstrap;

import com.sametsafkan.mssbeerservice.domain.Beer;
import com.sametsafkan.mssbeerservice.web.model.BeerStyle;
import com.sametsafkan.mssbeerservice.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200037";
    public static final String BEER_2_UPC = "0631234300018";
    public static final String BEER_3_UPC = "0083783375214";

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
                .upc(BEER_1_UPC)
                .build());
        beerRepository.save(new Beer().builder()
                .name("Efes")
                .style(BeerStyle.PILSENER.toString())
                .minOnHand(8)
                .quantityToBrew(100)
                .price(new BigDecimal(15.95))
                .upc(BEER_2_UPC)
                .build());
        beerRepository.save(new Beer().builder()
                .name("Corona")
                .style(BeerStyle.PALE_ALE.toString())
                .minOnHand(9)
                .quantityToBrew(100)
                .price(new BigDecimal(20.95))
                .upc(BEER_3_UPC)
                .build());
        System.out.println(beerRepository.count());
    }
}
