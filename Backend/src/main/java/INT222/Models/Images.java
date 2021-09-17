package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images")
public class Images {

    @Id
    private long id;

    @Column
    private String source;

    @Column
    private String label;





//
//    @ManyToOne
//    @JoinColumn(name = "productid")
//    private Products products;
}
