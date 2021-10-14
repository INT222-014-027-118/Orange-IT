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
@Table(name = "Products")
public class ProductCart {
    @Id
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column
    private double price;

    @Column(name = "quantity_stock")
    private int quantityStock;

    @JoinColumn(nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Discounts discount;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",insertable = true, updatable = true)
    private List<Images> images ;
}
