package INT222.Repositories;


import INT222.Models.Images;
import INT222.Models.ProductSpecValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecValueRepository extends JpaRepository<ProductSpecValues,Long> {

   // ProductSpecValues findTopByOrderByIdDesc();
}
