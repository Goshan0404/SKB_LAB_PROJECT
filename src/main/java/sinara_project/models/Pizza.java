package sinara_project.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;

    @ManyToMany(mappedBy = "pizzas")
    private Set<UserOrder> userOrders = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "pizza_constitution",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "constitution_id")
    )
    private Set<Constitution> constitutions = new HashSet<>();
}
