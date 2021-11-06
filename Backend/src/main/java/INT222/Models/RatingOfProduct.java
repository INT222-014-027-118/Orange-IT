package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rating_of_product")
public class RatingOfProduct {

    @Id
    private long id;


    @Column
    private String score;

    @JoinColumn(nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Ratings ratings;

    @Column
    private long product_id;


}
