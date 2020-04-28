package com.sametsafkan.mssbeerservice.listener;


import com.sametsafkan.brewery.event.BeerOrderValidationRequest;
import com.sametsafkan.brewery.event.BeerOrderValidationResponse;
import com.sametsafkan.mssbeerservice.config.JmsConfig;
import com.sametsafkan.mssbeerservice.service.order.BeerOrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderValidationRequestListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(BeerOrderValidationRequest request){
        boolean isValid = beerOrderValidator.validate(request.getBeerOrderDto());
        BeerOrderValidationResponse response = new BeerOrderValidationResponse(request.getBeerOrderDto().getId(), isValid);
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE, response);
    }
}
