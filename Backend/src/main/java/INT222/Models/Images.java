package INT222.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images")
public class Images {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String source;

    @Column
    private String label;

    @Column
   private long product_id;
}
