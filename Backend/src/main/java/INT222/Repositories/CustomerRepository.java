package INT222.Repositories;

import INT222.Models.Colors;
import INT222.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,String> {
}
