package INT222.Controllers;

import INT222.Models.Attributes;
import INT222.Models.Shippings;
import INT222.Repositories.ShippingRepository;
import INT222.Repositories.SpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingRepository shippingRepository;

    @GetMapping("/list")
    public List<Shippings> getShipping(){
        return shippingRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        shippingRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void editShipping(@RequestBody Shippings shippings) {
        shippingRepository.save(shippings);
    }

    @PostMapping("/add")
    public Shippings addShipping(@RequestBody Shippings shippings) {
        if(shippingRepository.findTopByOrderByIdDesc() == null) {
            shippings.setId(1);
            shippingRepository.save(shippings);
            return shippings;
        }else
            shippings.setId(shippingRepository.findTopByOrderByIdDesc().getId()+1);
        shippingRepository.save(shippings);
        return shippings;
    }

    @GetMapping("/getByDeliveryId/{id}")
    public List<Shippings> getShippingByDeliveryDetailId(@PathVariable(value = "id") long id){
        return shippingRepository.findAllByDeliveryDetailId(id);
    }

}
