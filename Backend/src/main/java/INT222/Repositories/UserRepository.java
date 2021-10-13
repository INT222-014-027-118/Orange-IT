package INT222.Repositories;


import INT222.Models.Products;
import INT222.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String name);

    Users findTopByOrderByIdDesc();
}
