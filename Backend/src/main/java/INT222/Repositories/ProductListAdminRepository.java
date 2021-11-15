package INT222.Repositories;

import INT222.Models.ProductListAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListAdminRepository extends JpaRepository<ProductListAdmin,Long> {
}
