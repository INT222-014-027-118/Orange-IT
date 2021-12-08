package INT222.Repositories;

import INT222.Models.RatingOfProductForAdd;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RatingOfProductForAddRepository extends JpaRepository<RatingOfProductForAdd, Long> {
        RatingOfProductForAdd findTopByOrderByIdDesc();

        @Transactional
        Long deleteByReviewId(long id);
}
