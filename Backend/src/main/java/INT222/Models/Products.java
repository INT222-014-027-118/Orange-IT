package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Products {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column
    private String description;

    @Column
    private double price;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "quantity_stock")
    private int quantityStock;


    @JoinColumn(nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Discounts discount;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "Products_has_colors", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Colors> colors = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "product_spec_values", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "spec_id"))
    private List<Specs>  specs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",insertable = true, updatable = true)
    private List<Images> images ;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "Products_has_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Categories> catergories = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",insertable = true, updatable = true)
    private List<ProductSpecValues> productSpecValues;


//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "rating_of_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "Ratings_id"))
//    private List<Ratings> ratings = new ArrayList<>();


}
