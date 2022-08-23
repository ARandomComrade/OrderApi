package com.am.orderapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderProvider orderProvider;

    @PostMapping("/new")
    public Order postOrder(@RequestBody Order order) {
        return orderProvider.post(order);
    }
    /*
    @GetMapping("/{id}")
    public String getOrder(@PathVariable int id) {
        return "";
    }
    */
}
