package com.xeTraining.orderservice.service;

import com.xeTraining.orderservice.controller.OrderController;
import com.xeTraining.orderservice.outputPojo.NewMagicWandPojo;
import com.xeTraining.orderservice.outputPojo.NewWizardPojo;
import com.xeTraining.orderservice.entity.Order;
import com.xeTraining.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/api/demo/wizardInfoList";
    String url2 = "http://localhost:8082/api/demo/magicWandList";
    String url3 = "http://localhost:8082/api/demo/updateMagicWand";
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Order> getOrder(){
        return repository.findAll();
    }

    public String addOrder(Order order){
        try {
            ResponseEntity<List<NewWizardPojo>> responseWizard = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<NewWizardPojo>>() {});

            ResponseEntity<List<NewMagicWandPojo>> responseMagic = restTemplate.exchange(url2, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<NewMagicWandPojo>>() {});

            for (NewWizardPojo wizardPojo : responseWizard.getBody()) {
                log.info(String.valueOf(wizardPojo.getWizard_name()));
                if (wizardPojo.getWizard_id() == order.getWizard_id()) {
                    log.info(String.valueOf(wizardPojo.getWizard_id()));
                    if (!wizardPojo.getActive_wizard().equals("Y")) {
                        return wizardPojo.getWizard_name() +
                                "'s status is not active";
                    }
                    order.setWizard_name(wizardPojo.getWizard_name());
                    order.setAge(wizardPojo.getAge());
                    for(NewMagicWandPojo magicWandPojo : responseMagic.getBody()){
                        if (magicWandPojo.getWand_id() == order.getWand_id()) {
                            if (order.getAge() > magicWandPojo.getAge_limit()) {
                                return wizardPojo.getWizard_name() + "'s age: "
                                        +order.getAge()+ " exceeds the " +
                                        magicWandPojo.getMagic_wand_name() +
                                        "'s age limit which is " + magicWandPojo.getAge_limit();
                            }
                            if (magicWandPojo.getMagic_wand_stock() <= 0) {
                                return magicWandPojo.getMagic_wand_name() + "'s stock unavailable";
                            }
                            order.setMagic_wand_name(magicWandPojo.getMagic_wand_name());
                            order.setMagic_wand_desc(magicWandPojo.getMagic_wand_desc());

                            order.setMagic_wand_stock(magicWandPojo.getMagic_wand_stock() - 1);
                            order.setAge_limit(magicWandPojo.getAge_limit());

                           // magicWandPojo.setWand_id(order.getOrder_id());
                            magicWandPojo.setMagic_wand_stock(order.getMagic_wand_stock());

                            HttpEntity<NewMagicWandPojo> request = new HttpEntity<>(magicWandPojo);
                            ResponseEntity<String> responseUpdate = restTemplate.exchange(url3 + "/" +
                                           order.getWand_id(), HttpMethod.PUT, request, String.class);

                            repository.save(order);
                            return "Order created successfully";
                        }
                    }
                    return "Wand id: " + order.getWand_id()+  " invalid";
                }
            }
            return "Wizard id: " + order.getWizard_id()+ " invalid";

        } catch (Exception e) {
            e.printStackTrace();
            return "Some technical Error. Please try again after some time";
        }
    }

    public void deleteOrder(Long id){
        repository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        repository.deleteById(id);
    }

    public Order updateOrder(Long order_id, Order order){
        Order foundOrder = repository.findById(order_id)
                .orElseThrow(() -> new RuntimeException(order_id + " not found")
        );
        foundOrder.setMagic_wand_name(order.getMagic_wand_name());
        foundOrder.setMagic_wand_desc(order.getMagic_wand_desc());
        foundOrder.setWizard_id(order.getWizard_id());
        foundOrder.setWand_id(order.getWand_id());
        foundOrder.setWizard_name(order.getWizard_name());
        foundOrder.setAge(order.getAge());
        foundOrder.setAge_limit(order.getAge_limit());
        foundOrder.setMagic_wand_stock(order.getMagic_wand_stock());
        return repository.save(foundOrder);
    }
}
