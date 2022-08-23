package com.am.orderapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("orderProvider")
public class OrderProvider {

    static final Logger logger = LoggerFactory.getLogger("OrderApiLogger");
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
        Double totalApple = 0.0;
        Double totalOrange = 0.0;
        for (Item item: itemList) {
           if (item.getName() == Fruit.APPLE && item.getQuantity() >= 2) {
               //buy one get one
              totalApple = (priceMap.get(Fruit.APPLE) * 0.5 ) * item.getQuantity();
              logger.debug(totalApple.toString());
              item.setPrice(priceMap.get(Fruit.APPLE) * 0.5);
           } else if (item.getName() == Fruit.ORANGE && item.getQuantity() >= 3) {
               //3 for 2 on all oranges
               totalOrange = (priceMap.get(Fruit.APPLE) * 0.75) * item.getQuantity();
               logger.debug(totalOrange.toString());
               item.setPrice(priceMap.get(Fruit.ORANGE) * 0.75);
           } else if (item.getName() == Fruit.APPLE){
               //normal price apple
               totalApple = priceMap.get(Fruit.APPLE) * item.getQuantity();
               logger.debug(totalApple.toString());
               item.setPrice(priceMap.get(Fruit.APPLE));
           } else if (item.getName() == Fruit.ORANGE) {
               //normalPriceOrange
               totalOrange = priceMap.get(Fruit.ORANGE) * item.getQuantity();
               logger.debug(totalOrange.toString());
               item.setPrice(priceMap.get(Fruit.ORANGE));
           }
        }
        total = totalApple + totalOrange;
        logger.debug(total.toString());
        recOrder.setItems(itemList);
        recOrder.setTotal(total);
        //return repository.save(order);
        return recOrder;
    }
}
