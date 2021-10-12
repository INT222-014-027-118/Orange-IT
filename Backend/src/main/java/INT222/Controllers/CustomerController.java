package INT222.Controllers;

import INT222.Models.Colors;
import INT222.Models.Customers;
import INT222.Repositories.ColorRepository;
import INT222.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/list")
    public List<Customers> getCustomer(){
        return customerRepository.findAll();
    }
}
