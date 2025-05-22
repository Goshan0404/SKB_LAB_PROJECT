package sinara_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sinara_project.metrics.UserOrderMetrics;
import sinara_project.models.order.UserOrderDto;
import sinara_project.service.OrderServiceProducer;


@RestController()
public class OrderController {

    @Autowired
    private OrderServiceProducer orderServiceProducer;

    @Autowired
    private UserOrderMetrics metrics;

    @PostMapping("/order/create")
    public void createOrder(@RequestBody UserOrderDto order) {
        orderServiceProducer.createOrder(order);
        metrics.increment(order);
    }
}