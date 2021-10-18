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
public class CartItems {

    @Id
    private long id;

    @Column
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductCart productCart;

    @OneToOne
    @JoinColumn(name = "color_id")
    private Colors colors;

    @Column(name = "users_id")
    private long userId;
}
