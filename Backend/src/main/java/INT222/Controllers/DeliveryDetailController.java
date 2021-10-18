package INT222.Controllers;

import INT222.Exceptions.NotFoundDeliveryDetailException;
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
        if (deliveryDetailRepository.existsById(id)) {
            deliveryDetailRepository.deleteById(id);
        }else throw new NotFoundDeliveryDetailException(id);
    }

    @PutMapping("/update")
    public DeliveryDetails editDeliveryDetails(@RequestBody DeliveryDetails deliveryDetails) {
        if(deliveryDetailRepository.existsById(deliveryDetails.getId())) {
            deliveryDetailRepository.save(deliveryDetails);
            return deliveryDetails;
        }else throw new NotFoundDeliveryDetailException(deliveryDetails.getId());
    }

    @PostMapping("/add")
    public DeliveryDetails addDeliveryDetails(@RequestBody DeliveryDetails deliveryDetails) {
        if(deliveryDetailRepository.findTopByOrderByIdDesc() == null ) {
            deliveryDetails.setId(1);
            deliveryDetailRepository.save(deliveryDetails);
            return deliveryDetails;
        }else
            deliveryDetails.setId(deliveryDetailRepository.findTopByOrderByIdDesc().getId()+1);
        deliveryDetailRepository.save(deliveryDetails);
        return deliveryDetails;
    }

    @GetMapping("/findByUserId/{id}")
    public List<DeliveryDetails> getDeliveryDetailListByUserId(@PathVariable(value = "id") long userId){
        return deliveryDetailRepository.findAllByUserId(userId);
    }

}
