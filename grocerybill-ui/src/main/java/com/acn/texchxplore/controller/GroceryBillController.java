package com.acn.texchxplore.controller;

import com.acn.texchxplore.model.GroceryBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RibbonClient(name = "grocery-bill", configuration = GroceryBillConfiguration.class)
public class GroceryBillController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/grocery")
    public ModelAndView getIndexPage() {
        GroceryBill regularBill = this.restTemplate.getForObject("http://grocery-bill/items/bill/regular", GroceryBill.class);
        GroceryBill discountedBill = this.restTemplate.getForObject("http://grocery-bill/items/bill/discounted", GroceryBill.class);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("grocery");
        mav.addObject("discountedBill", discountedBill);
        mav.addObject("clerk", regularBill.getClerk());
        mav.addObject("regularBill", regularBill);

        return mav;
    }

}