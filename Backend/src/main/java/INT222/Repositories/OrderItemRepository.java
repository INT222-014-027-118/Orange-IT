package INT222.Repositories;

import INT222.Models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {

    OrderItems findTopByOrderByIdDesc();

    @Transactional
    Long deleteByOrderId(long id);
}
