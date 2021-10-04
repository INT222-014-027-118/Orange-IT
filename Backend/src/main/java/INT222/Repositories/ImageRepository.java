package INT222.Repositories;

import INT222.Models.Images;
import INT222.Models.OrderItems;
import INT222.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images,Long> {

    Images findTopByOrderByIdDesc();

//    Images findFirstByProduct_id(long id);

}
