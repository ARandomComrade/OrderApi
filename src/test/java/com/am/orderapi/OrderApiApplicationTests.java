package com.am.orderapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderApiApplicationTests {

    @Autowired
    OrderProvider orderProvider;

    @Test
    void contextLoads() {
    }

    @Test
    void OrderProviderPostTest() {
        Order order = new Order();
        Order testOrder = new Order();
        List<Item> itemList = new ArrayList<>();
        Item apple = new Item();
        Item orange = new Item();
        apple.setName(Fruit.APPLE);
        apple.setQuantity(2);
        orange.setName(Fruit.ORANGE);
        orange.setQuantity(3);
        itemList.add(apple);
        itemList.add(orange);
        order.setItems(itemList);
        testOrder = orderProvider.post(order);
        Assertions.assertEquals(testOrder.getTotal(), 1.95);
    }

}
