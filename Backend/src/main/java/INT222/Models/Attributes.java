package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attributes")
public class Attributes {


    @Id
    private long id;

    @Column
    private String attribute;

//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "attribute_id",insertable = true, updatable = true)
//    private List<ProductsHasAttributes> productsHasAttributes;

}
