package INT222.Repositories;

import INT222.Models.RatingOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingOfProductRepository  extends JpaRepository<RatingOfProduct, Long> {

    List<RatingOfProduct> getAllByProductId(long id);
}
