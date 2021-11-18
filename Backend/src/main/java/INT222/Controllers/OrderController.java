package INT222.Controllers;

import INT222.Models.*;
import INT222.Repositories.DiscountRepository;
import INT222.Repositories.OrderForAddRepository;
import INT222.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderForAddRepository orderForAddRepository;

    @GetMapping("/list")
    public List<OrderForAdd> getOrder(){
        return orderForAddRepository.findAll();
    }

    @GetMapping("/getByUserId/{id}")
    @PreAuthorize("hasRole('User')")
    public List<Orders> getOrderByUserId(@PathVariable(value = "id") long id){
        List<Orders> ordersByUserId = new ArrayList<Orders>();
        List<Orders> orders = orderRepository.findAll();
        for (int i = 0; i < orders.size(); i++) {
            Payments payments = orders.get(i).getPayments();

                if(payments.getUserId() == id){
                    ordersByUserId.add(orders.get(i));
                }
            }

        return ordersByUserId;

    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public Orders addOrder(@RequestBody OrderForAdd orderForAdd){
        if(orderForAddRepository.findTopByOrderByIdDesc() == null){
            orderForAdd.setId(1);
            orderForAddRepository.save(orderForAdd);
            return orderRepository.getById(orderForAdd.getId());
        }else
            orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId()+1);
        orderForAddRepository.save(orderForAdd);
        return orderRepository.getById(orderForAdd.getId());
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('User')")
    public void deleteById(@PathVariable(value = "id") long id) {
        orderRepository.deleteById(id);
    }



}
