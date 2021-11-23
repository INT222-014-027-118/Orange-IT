package INT222.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    @Column(name = "payments_id")
    private long paymentsId;



}
