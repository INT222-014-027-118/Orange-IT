package INT222.Repositories;

import INT222.Models.ProductsHasAttributes;
import INT222.Models.ProductsHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHasAttributeRepository extends JpaRepository<ProductsHasAttributes,Long> {
}
