package INT222.Repositories;

import INT222.Models.ReviewForAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewForAddRepository extends JpaRepository<ReviewForAdd,Long> {


}
