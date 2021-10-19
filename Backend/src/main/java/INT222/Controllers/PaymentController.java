package INT222.Controllers;

import INT222.Models.Discounts;
import INT222.Models.Payments;
import INT222.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;


    @GetMapping("/list")
    public List<Payments> getPayment(){
        return paymentRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        paymentRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void editPayment(@RequestBody Payments payments) {
        paymentRepository.save(payments);
    }

    @PostMapping("/add")
    public void addPayment(@RequestBody Payments payments) {
        if (paymentRepository.findTopByOrderByIdDesc() == null) {
            payments.setId(1);
            paymentRepository.save(payments);
        }else
        payments.setId(paymentRepository.findTopByOrderByIdDesc().getId()+1);
        paymentRepository.save(payments);
    }

    @GetMapping("/getByUserId/{id}")
    public List<Payments> getPaymentByUserId(@PathVariable(value = "id") long id){
        return paymentRepository.findAllByUserId(id);
    }

}
