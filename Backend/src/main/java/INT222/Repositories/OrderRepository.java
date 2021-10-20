package INT222.Repositories;

import INT222.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Orders,Long> {
}
