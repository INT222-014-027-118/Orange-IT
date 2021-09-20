package INT222.Controllers;

import INT222.Models.Colors;
import INT222.Models.Discounts;
import INT222.Models.Reviews;
import INT222.Repositories.ColorRepository;
import INT222.Repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@CrossOrigin(origins = "*")
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping("/list")
    public List<Discounts> getDiscount(){
        return discountRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        discountRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void editReview(@RequestBody Discounts discounts) {
        discountRepository.save(discounts);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody Discounts discounts) {
        discountRepository.save(discounts);
    }
}
