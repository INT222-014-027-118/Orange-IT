package INT222.Repositories;


import INT222.Models.ProductsHasAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecValueRepository extends JpaRepository<ProductsHasAttributes,Long> {

   // ProductSpecValues findTopByOrderByIdDesc();
}
