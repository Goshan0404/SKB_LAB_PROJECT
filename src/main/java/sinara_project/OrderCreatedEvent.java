package sinara_project;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import sinara_project.models.UserOrder;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final UserOrder order;

    public OrderCreatedEvent(Object source, UserOrder order) {
        super(source);

        this.order = order;
    }

}
