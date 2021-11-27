package INT222.Controllers;

import INT222.Models.*;
import INT222.Repositories.DiscountRepository;
import INT222.Repositories.OrderForAddRepository;
import INT222.Repositories.OrderItemRepository;
import INT222.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderForAddRepository orderForAddRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin')")
    public List<OrderForAdd> getOrder(){
        return orderForAddRepository.findAll();
    }

    @GetMapping("/getByUserId/{id}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public List<Orders> getOrderByUserId(@PathVariable(value = "id") long id){
        return orderRepository.findAllByUserId(id);

    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public void addOrder(@RequestBody OrderForAdd orderForAdd){
        if(orderForAddRepository.findTopByOrderByIdDesc() == null){
            orderForAdd.setId(1);
            LocalDateTime now = LocalDateTime.now();
            orderForAdd.setOrderDate(now);
            for (int i = 0; i < orderForAdd.getOrderItems().size(); i++) {
                orderForAdd.getOrderItems().get(i).setOrderId(1);
                if(orderForAddRepository.findTopByOrderByIdDesc()== null) {
                    orderForAdd.setId(1);
                }else orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId() + 1);
            }
            orderForAddRepository.save(orderForAdd);

//            return orderRepository.getById(orderForAdd.getId());
        }else
            orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId()+1);
        LocalDateTime now = LocalDateTime.now();
        orderForAdd.setOrderDate(now);
        for (int i = 0; i < orderForAdd.getOrderItems().size(); i++) {
            orderForAdd.getOrderItems().get(i).setOrderId(orderForAdd.getId());
            if(orderForAddRepository.findTopByOrderByIdDesc()== null) {
                orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId());
            }else orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId() + 1);

        }
        orderForAddRepository.save(orderForAdd);
        //return orderRepository.getById(orderForAdd.getId());
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
this.deleteOrderItem(id);
        orderRepository.deleteById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editPayment(@RequestBody Orders orders) {
        if(orderRepository.existsById(orders.getId())) {
            orderRepository.save(orders);
        }
    }

    @DeleteMapping("/deleteOrderItem/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteOrderItem(@PathVariable long id) {
        orderItemRepository.deleteByOrderId(id);

    }

}
