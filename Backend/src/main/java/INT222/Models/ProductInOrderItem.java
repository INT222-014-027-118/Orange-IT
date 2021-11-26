package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class ProductInOrderItem {

    @Id
    private long id;

    @Column(name = "product_name")
    private String productName;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",insertable = true, updatable = true)
    private List<Images> images = new ArrayList<>();
}
