package INT222.Controllers;

import INT222.Exceptions.NotFoundOrderException;
import INT222.Exceptions.NotFoundOrderItemException;
import INT222.Models.OrderItemForAdd;
import INT222.Models.OrderItems;
import INT222.Models.Orders;
import INT222.Repositories.OrderItemForAddRepository;
import INT222.Repositories.OrderItemRepository;
import INT222.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemForAddRepository orderItemForAddRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin')")
    public List<OrderItems> getOrderItem(){
        return orderItemRepository.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public OrderItemForAdd addOrderItem(@RequestBody OrderItemForAdd orderItemForAdd) {
        if (!orderRepository.existsById(orderItemForAdd.getOrderId())) {
            throw new NotFoundOrderException(orderItemForAdd.getOrderId());
        }
            if (orderItemRepository.findTopByOrderByIdDesc() == null) {
                orderItemForAdd.setId(1);
                orderItemForAddRepository.save(orderItemForAdd);
                return orderItemForAddRepository.getById(orderItemForAdd.getId());
            } else
                orderItemForAdd.setId(orderItemRepository.findTopByOrderByIdDesc().getId() + 1);
            orderItemForAddRepository.save(orderItemForAdd);
            return orderItemForAddRepository.getById(orderItemForAdd.getId());

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        orderItemRepository.deleteById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editOrder(@RequestBody OrderItemForAdd orderItemForAdd) {
        if(!orderItemForAddRepository.existsById(orderItemForAdd.getId())) {

            throw new NotFoundOrderItemException(orderItemForAdd.getId());
        }else orderItemForAddRepository.save(orderItemForAdd);
    }
}
