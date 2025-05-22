package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.order.UserOrder;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findAllByUserId(Long userId);
}
