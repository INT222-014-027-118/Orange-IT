package INT222.Controllers;

import INT222.Models.*;
import INT222.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ThaiBuddhistDate;
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

    @Autowired
    private ProductStockRepository productStockRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin')")
    public List<Orders> getOrder(){
        return orderRepository.findAll();
    }

    @GetMapping("/getByUserId/{id}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public List<Orders> getOrderByUserId(@PathVariable(value = "id") long id){
        List<Orders> orders = new ArrayList<Orders>();
        for (long i = 1; i < orderRepository.findAll().size()+1; i++) {
                if(orderRepository.getById(i).getUsers().getId() == id){
                    orders.add(orderRepository.getById(i));
                }


            }return  orders;


    }

    @GetMapping("/getByUserId/{id}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public Orders getOrderById(@PathVariable(value = "id") long id){
       return orderRepository.getById(id);

    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    public void addOrder(@RequestBody OrderForAdd orderForAdd){
        if(orderForAddRepository.findTopByOrderByIdDesc() == null){
            orderForAdd.setId(1);
            ZoneId zone = ZoneId.of("Asia/Bangkok");
            LocalDateTime now = LocalDateTime.now(zone);
            orderForAdd.setOrderDate(now);
            long num = 1;
            for (int i = 0; i < orderForAdd.getOrderItems().size(); i++) {
                if(orderItemRepository.findTopByOrderByIdDesc() == null) {
                    orderForAdd.getOrderItems().get(i).setId(1);
                    ProductStock productStock =  productStockRepository.getById(orderForAdd.getOrderItems().get(i).getProductId());
                    int stock = productStock.getQuantityStock();
                    productStock.setQuantityStock((int) (stock - orderForAdd.getOrderItems().get(i).getQuantity()));
                    productStockRepository.save(productStock);
                }else orderForAdd.getOrderItems().get(i).setId(orderItemRepository.findTopByOrderByIdDesc().getId()+num);
                num++;
                ProductStock productStock =  productStockRepository.getById(orderForAdd.getOrderItems().get(i).getProductId());
                int stock = productStock.getQuantityStock();
                productStock.setQuantityStock((int) (stock - orderForAdd.getOrderItems().get(i).getQuantity()));
                productStockRepository.save(productStock);
                if(orderForAddRepository.findTopByOrderByIdDesc()== null) {
                    orderForAdd.setId(1);
                }else orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId() + 1);
            }
            orderForAddRepository.save(orderForAdd);

//            return orderRepository.getById(orderForAdd.getId());
        }else

            orderForAdd.setId(orderForAddRepository.findTopByOrderByIdDesc().getId()+1);
        long num = 1;
        LocalDateTime now = LocalDateTime.now();
        orderForAdd.setOrderDate(now);
        for (int i = 0; i < orderForAdd.getOrderItems().size(); i++) {
            if(orderItemRepository.findTopByOrderByIdDesc() == null) {
                orderForAdd.getOrderItems().get(i).setId(1);
                ProductStock productStock =  productStockRepository.getById(orderForAdd.getOrderItems().get(i).getProductId());
                int stock = productStock.getQuantityStock();
                productStock.setQuantityStock((int) (stock - orderForAdd.getOrderItems().get(i).getQuantity()));
                productStockRepository.save(productStock);
            }else orderForAdd.getOrderItems().get(i).setId(orderItemRepository.findTopByOrderByIdDesc().getId()+num);
            num++;
            ProductStock productStock =  productStockRepository.getById(orderForAdd.getOrderItems().get(i).getProductId());
            int stock = productStock.getQuantityStock();
            productStock.setQuantityStock((int) (stock - orderForAdd.getOrderItems().get(i).getQuantity()));
            productStockRepository.save(productStock);

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

    @PutMapping("/updateStatus/{status}/{id}")
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public void editOrderStatus(@PathVariable(value = "id") long id,@PathVariable(value = "status") String status) {
        if(orderRepository.existsById(id)) {
            OrderForAdd order = orderForAddRepository.getById(id);
            order.setStatus(status);
            orderForAddRepository.save(order);
        }
    }

    @DeleteMapping("/deleteOrderItem/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteOrderItem(@PathVariable long id) {
        orderItemRepository.deleteByOrderId(id);

    }

    //@PutMapping("/updateStock/{id}")

    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public void editOrder(@RequestBody OrderForAdd orderForAdd) {
        if(orderRepository.existsById(orderForAdd.getId())) {
            orderForAddRepository.save(orderForAdd);

        }
    }

}
