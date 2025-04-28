package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}
