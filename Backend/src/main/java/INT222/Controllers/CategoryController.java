package INT222.Controllers;

import INT222.Models.Categories;
import INT222.Models.Colors;
import INT222.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
