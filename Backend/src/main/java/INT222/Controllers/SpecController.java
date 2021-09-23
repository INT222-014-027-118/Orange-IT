package INT222.Controllers;

import INT222.Models.Categories;
import INT222.Models.Colors;
import INT222.Models.Specs;
import INT222.Repositories.SpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
@CrossOrigin(origins = "*")
public class SpecController {

    @Autowired
    private SpecRepository specRepository;

    @GetMapping("/list")
    public List<Specs> getSpec(){
        return specRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        specRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void editSpec(@RequestBody Specs specs) {
        specRepository.save(specs);
    }

    @PostMapping("/add")
    public void addSpec(@RequestBody Specs specs) {
        specRepository.save(specs);
    }
}
