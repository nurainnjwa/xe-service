package com.xeTraining.orderservice;

import com.xeTraining.orderservice.entity.Order;
import com.xeTraining.orderservice.outputPojo.NewMagicWandPojo;
import com.xeTraining.orderservice.outputPojo.NewWizardPojo;
import com.xeTraining.orderservice.repository.OrderRepository;
import com.xeTraining.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderTest {

//    @InjectMocks
//    OrderService orderService;

    @Mock
    OrderRepository repository;

    @Mock
    RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/api/demo/wizardInfoList";
    private static final String URL2 = "http://localhost:8082/api/demo/magicWandList";
    private static final String URL3 = "http://localhost:8082/api/demo/updateMagicWand";

    private List<NewWizardPojo> wizardList;
    private List<NewMagicWandPojo> magicWandList;


    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    @InjectMocks
    private OrderService orderService = new OrderService(restTemplateBuilder);

    @Test
    public void testAddOrderSuccessful() {

        Order order = new Order();
        order.setWizard_id(4L);
        order.setWand_id(6L);

        // Set up mock restTemplate
        NewWizardPojo wizard = new NewWizardPojo();
        wizard.setWizard_id(4L);
        wizard.setWizard_name("Harry Potter");
        wizard.setAge(30);
        wizard.setActive_wizard("Y");

        NewMagicWandPojo magicWand = new NewMagicWandPojo();
        magicWand.setWand_id(6L);
        magicWand.setMagic_wand_name("Red Wand");
        magicWand.setMagic_wand_desc("A celeb Wand");
        magicWand.setAge_limit(40);
        magicWand.setMagic_wand_stock(16);

        ResponseEntity<List<NewWizardPojo>> responseWizard =
                new ResponseEntity<List<NewWizardPojo>>(Arrays.asList(wizard), HttpStatus.OK);

        ResponseEntity<List<NewMagicWandPojo>> responseMagic =
                new ResponseEntity<List<NewMagicWandPojo>>(Arrays.asList(magicWand), HttpStatus.OK);

        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseWizard);
        when(mockRestTemplate.exchange(eq(URL2), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseMagic);

        // Inject mock restTemplate into the service class
        orderService.restTemplate = mockRestTemplate;

        String result = orderService.addOrder(order);

        assertEquals("Order created successfully", result);

    }

    @Test
    public void testAddOrder_WizardNotActive() {
        Order order = new Order();
        order.setWizard_id(4L);
        order.setWand_id(1L);

        // Set up mock restTemplate to return inactive wizard
        NewWizardPojo inactiveWizard = new NewWizardPojo();
        inactiveWizard.setWizard_id(4L);
        inactiveWizard.setWizard_name("Harry Potter");
        inactiveWizard.setAge(30);
        inactiveWizard.setActive_wizard("N");

        ResponseEntity<List<NewWizardPojo>> responseWizard =
                new ResponseEntity<List<NewWizardPojo>>(Arrays.asList(inactiveWizard), HttpStatus.OK);

        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseWizard);
        when(mockRestTemplate.exchange(eq(URL2), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<List<NewMagicWandPojo>>(new ArrayList<>(), HttpStatus.OK));

        // Inject mock restTemplate into the service class
        orderService.restTemplate = mockRestTemplate;

        String result = orderService.addOrder(order);
        assertEquals("Harry Potter's status is not active", result);
    }

    @Test
    public void testAddOrder_WizardAgeExceedsAgeLimit(){
        Order order = new Order();
        order.setWizard_id(4L);
        order.setWand_id(6L);

        // Set up mock restTemplate
        NewWizardPojo wizard = new NewWizardPojo();
        wizard.setWizard_id(4L);
        wizard.setWizard_name("Harry Potter");
        wizard.setAge(30);
        wizard.setActive_wizard("Y");

        NewMagicWandPojo magicWand = new NewMagicWandPojo();
        magicWand.setWand_id(6L);
        magicWand.setMagic_wand_name("Red Wand");
        magicWand.setMagic_wand_desc("A celeb Wand");
        magicWand.setAge_limit(20);
        magicWand.setMagic_wand_stock(16);

        ResponseEntity<List<NewWizardPojo>> responseWizard =
                new ResponseEntity<List<NewWizardPojo>>(Arrays.asList(wizard), HttpStatus.OK);

        ResponseEntity<List<NewMagicWandPojo>> responseMagic =
                new ResponseEntity<List<NewMagicWandPojo>>(Arrays.asList(magicWand), HttpStatus.OK);

        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseWizard);
        when(mockRestTemplate.exchange(eq(URL2), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseMagic);

        // Inject mock restTemplate into the service class
        orderService.restTemplate = mockRestTemplate;

        String result = orderService.addOrder(order);

        assertEquals("Harry Potter's age: 30 exceeds the Red Wand's age limit which is 20", result);
    }

    @Test
    public void testAddOrder_MagicWandNoStock(){
        Order order = new Order();
        order.setWizard_id(4L);
        order.setWand_id(6L);

        // Set up mock restTemplate
        NewWizardPojo wizard = new NewWizardPojo();
        wizard.setWizard_id(4L);
        wizard.setWizard_name("Harry Potter");
        wizard.setAge(30);
        wizard.setActive_wizard("Y");

        NewMagicWandPojo magicWand = new NewMagicWandPojo();
        magicWand.setWand_id(6L);
        magicWand.setMagic_wand_name("Red Wand");
        magicWand.setMagic_wand_desc("A celeb Wand");
        magicWand.setAge_limit(30);
        magicWand.setMagic_wand_stock(0);

        ResponseEntity<List<NewWizardPojo>> responseWizard =
                new ResponseEntity<List<NewWizardPojo>>(Arrays.asList(wizard), HttpStatus.OK);

        ResponseEntity<List<NewMagicWandPojo>> responseMagic =
                new ResponseEntity<List<NewMagicWandPojo>>(Arrays.asList(magicWand), HttpStatus.OK);

        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.exchange(eq(URL), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseWizard);
        when(mockRestTemplate.exchange(eq(URL2), eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseMagic);

        // Inject mock restTemplate into the service class
        orderService.restTemplate = mockRestTemplate;

        String result = orderService.addOrder(order);

        assertEquals("Red Wand's stock unavailable", result);
    }

    @Test
    public void testGetOrder() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(20L,2L, 2L, "Yoon", 30,
                "Harry Potter Wand", "A harry potter's theme wand",
                25, 50));
        orders.add(new Order(20L,2L, 2L, "Isa", 20,
                "Sailor Moon Wand", "A Sailor Moon's theme wand",
                25, 30));

        when(repository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getOrder();
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteOrder(){
        Order orders = (new Order(20L,2L, 2L, "Yoon", 30,
                "Harry Potter Wand", "A harry potter's theme wand",
                25, 50));
        when(repository.findById(20L)).thenReturn(Optional.of(orders));
        String result = orderService.deleteOrder(20L);
        assertEquals("Order successfully deleted", result);
    }

    @Test
    public void testDeleteOrder_OrderIDNotFound(){
        try{
            Order orders = (new Order(1L,2L, 2L, "Yoon", 30,
                    "Harry Potter Wand", "A harry potter's theme wand",
                    25, 50));
            when(repository.findById(20L)).thenReturn(Optional.of(orders));
            String result = orderService.deleteOrder(1L);
        }catch (Exception e){
            assertEquals("Order ID not found", e.getMessage());
        }

    }
}

