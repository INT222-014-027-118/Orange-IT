package INT222.Repositories;

import INT222.Models.ProductsHasAttributes;
import INT222.Models.ProductsHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductHasAttributeRepository extends JpaRepository<ProductsHasAttributes,Long> {

    ProductsHasAttributes findTopByOrderByIdDesc();

    @Transactional
    Long deleteByProductId(long id);

}
