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

    @Column(name = "price")
    private float price;


    @ManyToOne()
    @JoinColumn(name = "product_id")
    private ProductInOrderItem product;


    @Column(name = "order_id")
    private long orderId;

    @OneToOne()
    @JoinColumn(name = "color_id")
    private Colors color;







}
