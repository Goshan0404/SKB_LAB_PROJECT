package sinara_project.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
