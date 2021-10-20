package INT222.Controllers;

import INT222.Exceptions.NotFoundCategoryException;
import INT222.Models.Attributes;
import INT222.Models.CartItemForAdd;
import INT222.Models.Categories;
import INT222.Repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeRepository attributeRepository;


    @GetMapping("/list")
    public List<Attributes> getAllAttribute(){
        return attributeRepository.findAll();
    }

    @PostMapping("/add")
    public Attributes addAttribute(@RequestBody Attributes attributes) {

        if (attributeRepository.findTopByOrderByIdDesc() == null){
            attributes.setId(1);
            attributeRepository.save(attributes);
            return attributes;
        }else
            attributes.setId(attributeRepository.findTopByOrderByIdDesc().getId()+1);
        attributeRepository.save(attributes);
        return attributes;

    }  @PutMapping("/update")
    public void editAttribute(@RequestBody Attributes attributes) {
        if (attributeRepository.existsById(attributes.getId())) {
            attributeRepository.save(attributes);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        attributeRepository.deleteById(id);
    }


}
