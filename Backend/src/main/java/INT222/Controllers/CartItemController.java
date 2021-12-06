package INT222.Controllers;

import INT222.Exceptions.NotFoundCartItemException;
import INT222.Exceptions.NotFoundUserCartItemException;
import INT222.Exceptions.NotFoundUserException;
import INT222.Exceptions.ProductOutOffStock;
import INT222.Models.CartItemForAdd;
import INT222.Models.CartItems;
import INT222.Repositories.CartItemForAddRepository;
import INT222.Repositories.CartItemRepository;
import INT222.Repositories.ProductRepository;
import INT222.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/cartItem")
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemForAddRepository cartItemForAddRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('User')")
    public void deleteById(@PathVariable(value = "id") long id) {
        cartItemRepository.deleteById(id);
    }

    @PostMapping("/add_item/{userId}/{productId}")
    @PreAuthorize("hasRole('User')")
    public CartItems addCartItem(@RequestBody CartItemForAdd cartItemForAdd,@PathVariable(value = "userId") long userId,
                            @PathVariable(value = "productId") long productId) {
        if(!userRepository.existsById(userId)){
            throw new NotFoundUserException(userId);
        }
        int stock = productRepository.getById(productId).getQuantityStock() - cartItemForAdd.getQuantity();
        if(stock <= 0){
            throw new ProductOutOffStock(productId);
        }
            cartItemForAdd.setProductId(productId);
            cartItemForAdd.setUserId(userId);
            if (cartItemForAddRepository.findTopByOrderByIdDesc() == null) {
                cartItemForAdd.setId(1);
                cartItemForAddRepository.save(cartItemForAdd);
            } else {
                long id = cartItemForAddRepository.findTopByOrderByIdDesc().getId() + 1;
                cartItemForAdd.setId(id);
                cartItemForAddRepository.save(cartItemForAdd);
            }
            return cartItemRepository.getById(cartItemForAdd.getId());

    }

//    @GetMapping("/list")
//    public List<CartItemForAdd> getCartItemForAddList(){
//        return cartItemForAddRepository.findAll();
//    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('User')")
    public void editCartItem(@RequestBody CartItemForAdd cartItemForAdd) {
        if(cartItemForAddRepository.existsById(cartItemForAdd.getId())){
        cartItemForAddRepository.save(cartItemForAdd);
    }else throw new NotFoundCartItemException(cartItemForAdd.getId());
    }

    @GetMapping("/findByUserId/{id}")
    @PreAuthorize("hasRole('User')")
    public List<CartItems> getCartItemListByUserId(@PathVariable(value = "id") long userId) {
            return cartItemRepository.findAllByUserId(userId);


    }


}
