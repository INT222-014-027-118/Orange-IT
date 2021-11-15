package INT222.Repositories;

import INT222.Models.Colors;
import INT222.Models.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discounts,Long> {

    Discounts findTopByOrderByIdDesc();
}
