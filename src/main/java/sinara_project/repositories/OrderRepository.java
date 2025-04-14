package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
