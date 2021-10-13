package INT222.Repositories;

import INT222.Models.ProductsHome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHomeRepository  extends JpaRepository<ProductsHome,Long> {

    Page<ProductsHome> findAll(Pageable pageable);
}
