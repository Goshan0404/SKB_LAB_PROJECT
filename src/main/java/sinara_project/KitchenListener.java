package sinara_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KitchenListener {

    @EventListener
    public void handleOrderEvent(OrderCreatedEvent orderCreatedEvent) {
        log.info("Обработка ивента создания заказа");
    }

}
