package INT222.Controllers;

import INT222.Exceptions.NotFoundAttributeException;
import INT222.Exceptions.NotFoundCategoryException;
import INT222.Exceptions.SameAttributeException;
import INT222.Models.Attributes;
import INT222.Models.CartItemForAdd;
import INT222.Models.Categories;
import INT222.Repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeRepository attributeRepository;


    @GetMapping("/list")
    public List<Attributes> getAllAttribute(){
        return attributeRepository.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public Attributes addAttribute(@RequestBody Attributes attributes) {

if(!attributeRepository.existsByAttribute(attributes.getAttribute())) {
    if (attributeRepository.findTopByOrderByIdDesc() == null) {
        attributes.setId(1);
        attributeRepository.save(attributes);
        return attributes;
    } else
        attributes.setId(attributeRepository.findTopByOrderByIdDesc().getId() + 1);
    attributeRepository.save(attributes);
    return attributes;
}else throw new SameAttributeException(attributes.getAttribute());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editAttribute(@RequestBody Attributes attributes) {
        if(attributes.getAttribute().equals(attributeRepository.findById(attributes.getId()).get().getAttribute())) {

            if (attributeRepository.existsById(attributes.getId())) {
                attributeRepository.save(attributes);
            } else
                throw new NotFoundAttributeException(attributes.getId());
        }else if (!attributeRepository.existsByAttribute(attributes.getAttribute())) {
            attributeRepository.save(attributes);
            }else throw new SameAttributeException(attributes.getAttribute());

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        if(attributeRepository.existsById(id)) {
            attributeRepository.deleteById(id);
        }else throw new NotFoundAttributeException(id);
    }


}
