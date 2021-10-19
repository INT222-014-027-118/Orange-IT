package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_items")
public class OrderItems {

    @Id
    private long id;

    @Column
    private long quantity;

    @Column(name = "discount_price")
    private float discountPrice;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true,name = "product_id")
    private ProductCart products;





}
