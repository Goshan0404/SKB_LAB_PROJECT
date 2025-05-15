package sinara_project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import sinara_project.models.UserOrder;
import sinara_project.models.user.UserApp;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class OrderServiceProducer {

    private KafkaTemplate<String, UserOrder> kafkaTemplate;
    private final String orderCreatedTopic;

    public OrderServiceProducer(KafkaTemplate<String, UserOrder> kafkaTemplate, @Value("${kafka.topic.order-created}") String orderCreatedTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderCreatedTopic = orderCreatedTopic;
    }

    public void createOrder(UserOrder order, String userId) {
        log.info("Order is creating");
        log.info("Creating event");

        try {
            SendResult<String, UserOrder> s =
                    kafkaTemplate.send(orderCreatedTopic, userId, order).get();
        } catch (InterruptedException | ExecutionException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
