package INT222.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class OrderForAdd {


    @Id
    private long id;

    @Column
    private String status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "shipping_id")
    private long shippingId;

    @Column(name = "users_id")
    private long userId;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",insertable = true, updatable = true)
    private List<OrderItemForAdd> orderItems;


}
