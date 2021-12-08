package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reviews")
public class Reviews {

    @Id
    private long id;
    @Column
    private int star;
    @Column
    private String comment;
    @Column(name = "review_date")
    private String reviewDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private UserForReview userForReview;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "reviews_id")
    private List<RatingOfProduct> ratingOfProducts = new ArrayList<>();

}
