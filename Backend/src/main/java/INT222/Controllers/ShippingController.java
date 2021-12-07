package INT222.Controllers;

import INT222.Models.Attributes;
import INT222.Models.Shippings;
import INT222.Repositories.ShippingRepository;
import INT222.Repositories.SpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingRepository shippingRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin')")
    public List<Shippings> getShipping(){
        return shippingRepository.findAll();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        shippingRepository.deleteById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editShipping(@RequestBody Shippings shippings) {
        shippingRepository.save(shippings);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public void addShipping(@RequestBody Shippings shippings) {
        if(shippingRepository.findTopByOrderByIdDesc() == null) {
            shippings.setId(1);
            shippingRepository.save(shippings);

        }else
            shippings.setId(shippingRepository.findTopByOrderByIdDesc().getId()+1);
        shippingRepository.save(shippings);

    }

    @GetMapping("/getByDeliveryId/{id}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public List<Shippings> getShippingByDeliveryDetailId(@PathVariable(value = "id") long id){
        return shippingRepository.findAllByDeliveryDetailId(id);
    }

}
