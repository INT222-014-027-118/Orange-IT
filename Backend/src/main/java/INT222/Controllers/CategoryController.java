package INT222.Controllers;

import INT222.Exceptions.NotFoundCategoryException;
import INT222.Exceptions.SameCategoryException;
import INT222.Models.Categories;
import INT222.Models.Colors;
import INT222.Models.DeliveryDetails;
import INT222.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/list")
    public List<Categories> getCategories(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        categoryRepository.deleteById(id);
    }

    @PutMapping("/update")
    public Categories editCategory(@RequestBody Categories categories) {
        categoryRepository.save(categories);
        return categories;
    }

    @PostMapping("/add")
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
