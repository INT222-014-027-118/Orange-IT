package INT222.Repositories;


import INT222.Models.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Long> {
    Ratings findTopByOrderByIdDesc();

    List<Ratings> findAllByCategoryId(long id);
}
