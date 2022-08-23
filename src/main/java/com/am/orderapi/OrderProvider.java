package com.am.orderapi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("orderProvider")
public class OrderProvider {
    private final OrderRepository repository;

    private final HashMap<Fruit, Double> priceMap = new HashMap<>();

    public OrderProvider(OrderRepository repository) {
        init();
        this.repository = repository;
    }

    void init() {
        priceMap.put(Fruit.APPLE, .60);
        priceMap.put(Fruit.ORANGE, .25);
    }

    public Order post(Order order) {
        Order recOrder = new Order();
        List<Item> itemList = order.getItems();
        Double total = 0.0;
        for (Item item: itemList) {
           total += item.getQuantity() * priceMap.get(item.getName());
           item.setPrice(priceMap.get(item.getName()));
        }
        recOrder.setItems(itemList);
        recOrder.setTotal(total);
        //return repository.save(order);
        return recOrder;
    }
}
