package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_items")
public class OrderItemForAdd {

    @Id
    private long id;

    @Column
    private long quantity;

    @Column(name = "discount_price")
    private float discountPrice;


    @Column(name = "product_id")
    private long productId;


    @Column(name = "order_id")
    private long orderId;
}
