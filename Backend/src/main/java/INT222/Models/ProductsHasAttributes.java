package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_has_attributes")
public class ProductsHasAttributes {
    @Id
    private long attribute_id;

    @Column
    private long product_id;



    @Column(name = "attribute_value")
    private String attribute_value;
}
