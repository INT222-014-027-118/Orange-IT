package INT222.Controllers;

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
    public void editCategory(@RequestBody Categories categories) {
        categoryRepository.save(categories);
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody Categories categories) {
        categoryRepository.save(categories);
    }


}
