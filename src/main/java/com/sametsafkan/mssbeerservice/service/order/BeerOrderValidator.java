package com.sametsafkan.mssbeerservice.service.order;

import com.sametsafkan.brewery.model.BeerOrderDto;
import com.sametsafkan.brewery.model.BeerOrderLineDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class BeerOrderValidator {

    public boolean validate(BeerOrderDto beerOrderDto){
        List<BeerOrderLineDto> beerOrderLines = beerOrderDto.getBeerOrderLines();
        boolean isValid = true;
        for(BeerOrderLineDto line : beerOrderLines){
            if(StringUtils.isEmpty(line.getUpc())){
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
