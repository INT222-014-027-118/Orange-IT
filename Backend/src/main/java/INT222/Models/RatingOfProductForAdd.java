package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rating_of_product")
public class RatingOfProductForAdd {


    @Id
    private long id;


    @Column
    private String score;

    @Column
    private long ratings_id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "reviews_id")
    private long reviewId;
}
