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
    private String quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductCart productCart;

//    @Column long users_id;


}