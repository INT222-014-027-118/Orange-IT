package INT222.Repositories;

import INT222.Models.RatingOfProductForAdd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingOfProductForAddRepository extends JpaRepository<RatingOfProductForAdd, Long> {
        RatingOfProductForAdd findTopByOrderByIdDesc();
}
