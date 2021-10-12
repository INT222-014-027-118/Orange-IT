package INT222.Repositories;


import INT222.Models.Attributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<Attributes,Long> {
}
