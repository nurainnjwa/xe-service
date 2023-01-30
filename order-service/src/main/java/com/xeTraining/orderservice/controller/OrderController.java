package com.xeTraining.orderservice.controller;

import com.xeTraining.orderservice.entity.Order;
import com.xeTraining.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class OrderController {

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    OrderService service;

    private Order order = new Order();

    String url = "http://localhost:8080/api/demo/wizardInfoList";
    String url2 = "http://localhost:8082/api/demo/magicWandList";

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    HttpHeaders headers = new HttpHeaders();
    //headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    public OrderController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/getAllOrder")
    public List<Order> orderResponse() {
        return service.getOrder();
    }

    @PostMapping("/add")
    public String createOrder(@RequestBody Order order){
        return service.addOrder(order);
    }

//    @GetMapping("/delete{id}")
//    public void deleteOrder(@PathVariable(value = "id")long id){
//        service.deleteOrder(id);
//    }
//
//    @PutMapping("/update/{id}")
//    public Order updateWizardInfo(@RequestBody Order order){
//        return service.updateOrder(order);
//    }
    //}
}
