package INT222.Controllers;

import INT222.Exceptions.DiscountActiveException;
import INT222.Exceptions.NotFoundDiscountException;
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
        if(this.discountRepository.findById(id).get().getActive() == 0) {
            discountRepository.deleteById(id);
        }else throw new DiscountActiveException(id);
    }

    @PutMapping("/update")
    public void editDiscount(@RequestBody Discounts discounts) {
        if(discountRepository.existsById(discounts.getId())) {
            discountRepository.save(discounts);
        }else throw new NotFoundDiscountException(discounts.getId());
    }

    @PostMapping("/add")
    public void addDiscount(@RequestBody Discounts discounts) {
        if (discountRepository.findTopByOrderByIdDesc() == null) {
            discounts.setId(1);
            discountRepository.save(discounts);
        }else
            discounts.setId(discountRepository.findTopByOrderByIdDesc().getId()+1);
        discountRepository.save(discounts);
    }
}
