package com.at.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author user
 * @create 2021-05-28 22:07
 */
@RestController
@Slf4j
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serverUrl+"/payment/nacos/" + id,String.class);
    }

}
