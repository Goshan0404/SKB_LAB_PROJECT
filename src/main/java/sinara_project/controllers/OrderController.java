package sinara_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sinara_project.aspect.RequestLimit;
import sinara_project.metrics.UserOrderMetrics;
import sinara_project.models.order.UserOrderDto;
import sinara_project.repositories.OrderRepository;
import sinara_project.service.OrderServiceProducer;


@RestController()
public class OrderController {

    @Autowired
    private OrderServiceProducer orderServiceProducer;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserOrderMetrics metrics;

    @RequestLimit
    @PostMapping("/order/create")
    public void createOrder(@RequestBody UserOrderDto order) {
        orderServiceProducer.createOrder(order);
        metrics.increment(order);
    }

    @GetMapping("order")
    public void getUserOrders(@RequestBody Long userId) {
        orderRepository.findAllByUserId(userId);
    }
}