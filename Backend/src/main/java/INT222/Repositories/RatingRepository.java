package INT222.Repositories;


import INT222.Models.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RatingRepository extends JpaRepository<Ratings, Long> {
    Ratings findTopByOrderByIdDesc();




}
