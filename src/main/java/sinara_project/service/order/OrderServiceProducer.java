package sinara_project.service.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import sinara_project.models.order.UserOrderDto;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class OrderServiceProducer {

    private KafkaTemplate<String, UserOrderDto> kafkaTemplate;
    private final String orderCreatedTopic;

    @Autowired
    private OrderModelService modelService;

    public OrderServiceProducer(KafkaTemplate<String, UserOrderDto> kafkaTemplate, @Value("${kafka.topic.order-created}") String orderCreatedTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderCreatedTopic = orderCreatedTopic;
    }

    public void createOrder(UserOrderDto order) {
        log.info("Saving order in db");
        modelService.mapAndSave(order);

        log.info("Creating event for order");
        try {
            SendResult<String, UserOrderDto> s =
                    kafkaTemplate.send(orderCreatedTopic, String.valueOf(order.getUserId()), order).get();
            log.info("Order is sent");
        } catch (InterruptedException | ExecutionException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
