package sinara_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sinara_project.aspect.RequestLimit;
import sinara_project.metrics.UserOrderMetrics;
import sinara_project.models.order.UserOrderDto;
import sinara_project.repositories.OrderRepository;
import sinara_project.service.OrderService;
import sinara_project.service.OrderServiceProducer;


@RestController()
public class OrderController {

    @Autowired
    private OrderServiceProducer orderServiceProducer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserOrderMetrics metrics;

    @RequestLimit
    @PostMapping("/order/create")
    public void createOrder(@RequestBody UserOrderDto order) {
        orderServiceProducer.createOrder(order);
        metrics.increment(order);
    }

    @GetMapping("order")
    public Page<UserOrderDto> getUserOrders(@RequestBody Long userId, @RequestParam("offset") Integer offset,
                              @RequestParam("limit") Integer limit) {
        return orderService.getUserOrders(userId, PageRequest.of(offset, limit));
    }
}