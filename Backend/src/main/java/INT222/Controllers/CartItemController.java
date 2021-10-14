package INT222.Controllers;

import INT222.Models.CartItemForAdd;
import INT222.Models.CartItems;
import INT222.Models.Categories;
import INT222.Repositories.CartItemForAddRepository;
import INT222.Repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
@CrossOrigin(origins = "*")
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemForAddRepository cartItemForAddRepository;

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable(value = "id") long id) {
        cartItemRepository.deleteById(id);
    }

    @PostMapping("/add_item/{userId}/{productId}")
    public CartItems addCartItem(@RequestBody CartItemForAdd cartItemForAdd,@PathVariable(value = "userId") long userId,
                            @PathVariable(value = "productId") long productId) {
        cartItemForAdd.setProductId(productId);
        cartItemForAdd.setUserId(userId);
        if(cartItemForAddRepository.findTopByOrderByIdDesc() == null){
            cartItemForAdd.setId(1);
            cartItemForAddRepository.save(cartItemForAdd);
        }else {
            long id = cartItemForAddRepository.findTopByOrderByIdDesc().getId() + 1;
            cartItemForAdd.setId(id);
            cartItemForAddRepository.save(cartItemForAdd);
        }
        return cartItemRepository.getById(cartItemForAdd.getId());

    }

    @GetMapping("/list")
    public List<CartItemForAdd> getCartItemForAddList(){
        return cartItemForAddRepository.findAll();
    }

    @PutMapping("/update")
    public void editCartItem(@RequestBody CartItemForAdd cartItemForAdd) {
        cartItemForAddRepository.save(cartItemForAdd);
    }


}
