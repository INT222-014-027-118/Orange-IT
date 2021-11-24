package INT222.Repositories;

import INT222.Models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {

      List<CartItems> findAllByUserId(long id);

      Boolean existsByUserId(long id);

}
