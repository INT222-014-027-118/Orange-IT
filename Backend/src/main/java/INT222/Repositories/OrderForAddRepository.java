package INT222.Repositories;


import INT222.Models.OrderForAdd;
import INT222.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForAddRepository extends JpaRepository<OrderForAdd,Long> {

    OrderForAdd findTopByOrderByIdDesc();
}
