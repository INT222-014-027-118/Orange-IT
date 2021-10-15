package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cart_items")
public class CartItemForAdd {
    @Id
    private long id;

    @Column
    private String quantity;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "users_id")
    private long userId;


    @Column(name = "color_id")
    private long colorId;
}
