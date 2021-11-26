package INT222.Repositories;

import INT222.Models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;



@Repository
public interface ImageRepository extends JpaRepository<Images,Long> {

    Images findTopByOrderByIdDesc();

    Boolean existsImagesBySource(String source);

    @Transactional
    Long deleteByProductId(long id);






}
