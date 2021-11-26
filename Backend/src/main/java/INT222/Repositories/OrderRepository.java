package INT222.Repositories;

import INT222.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Orders,Long> {

    List<Orders> findAllByUserId(long id);
}
