package INT222.Repositories;

import INT222.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Long> {
//    Get root Categories
//    @Query("SELECT e from Categories e where e.parentId = null ")
//    List<Categories> findAll();
}
