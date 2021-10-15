package INT222.Controllers;

import INT222.Models.CartItems;
import INT222.Models.DeliveryDetails;
import INT222.Repositories.DeliveryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "*")
public class DeliveryDetailController {

    @Autowired
    private DeliveryDetailRepository deliveryDetailRepository;


    @GetMapping("/list")
    public List<DeliveryDetails> getDeliveryDetails() {
        return deliveryDetailRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        deliveryDetailRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void editDeliveryDetails(@RequestBody DeliveryDetails deliveryDetails) {
        deliveryDetailRepository.save(deliveryDetails);
    }

    @PostMapping("/add")
    public void addDeliveryDetails(@RequestBody DeliveryDetails deliveryDetails) {
        deliveryDetailRepository.save(deliveryDetails);
    }

    @GetMapping("/findByUserId/{id}")
    public List<DeliveryDetails> getDeliveryDetailListByUserId(@PathVariable(value = "id") long userId){
        return deliveryDetailRepository.findAllByUserId(userId);
    }

}
