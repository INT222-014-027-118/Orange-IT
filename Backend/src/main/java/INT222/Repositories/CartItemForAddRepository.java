package INT222.Repositories;

import INT222.Models.CartItemForAdd;
import INT222.Models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemForAddRepository extends JpaRepository<CartItemForAdd,Long> {

    CartItemForAdd findTopByOrderByIdDesc();
}
