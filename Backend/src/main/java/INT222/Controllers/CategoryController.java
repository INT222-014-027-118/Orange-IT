package INT222.Controllers;

import INT222.Exceptions.NotFoundCategoryException;
import INT222.Exceptions.SameCategoryException;
import INT222.Models.Categories;
import INT222.Models.Colors;
import INT222.Models.DeliveryDetails;
import INT222.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/list")
    public List<Categories> getCategories(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        categoryRepository.deleteById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public Categories editCategory(@RequestBody Categories categories) {
        if (categoryRepository.existsAllByCategory(categories.getCategory())) {
            throw new SameCategoryException(categories.getCategory());
        }
        categoryRepository.save(categories);
        return categories;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public Categories addCategory(@RequestBody Categories categories) {
        if (categoryRepository.existsAllByCategory(categories.getCategory())) {
        throw new SameCategoryException(categories.getCategory());
        }

        if (categoryRepository.findTopByOrderByIdDesc() == null){
            categories.setId(1);
            categoryRepository.save(categories);
            return categories;
        }else
            categories.setId(categoryRepository.findTopByOrderByIdDesc().getId()+1);
        categoryRepository.save(categories);
        return categories;
    }


}
