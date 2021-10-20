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
@Table(name = "Orders")
public class Orders {

    @Id
    private long id;

    @Column
    private String status;

    @Column(name = "order_date")
    private String orderDate;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",insertable = true, updatable = true)
    private List<OrderItems> orderItems;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true,name = "payments_id")
    private Payments payments;




}
