package INT222.Repositories;

import INT222.Models.Images;
import INT222.Models.OrderItems;
import INT222.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Images,Long> {

    Images findTopByOrderByIdDesc();

    Boolean existsImagesBySource(String source);




}
