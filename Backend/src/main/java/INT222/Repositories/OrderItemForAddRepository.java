package INT222.Repositories;

import INT222.Models.OrderItemForAdd;
import INT222.Models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderItemForAddRepository extends JpaRepository<OrderItemForAdd,Long> {
}
