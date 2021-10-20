package INT222.Repositories;

import INT222.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Long> {

    Categories findTopByOrderByIdDesc();

    Boolean existsAllByCategory(String name);

}
