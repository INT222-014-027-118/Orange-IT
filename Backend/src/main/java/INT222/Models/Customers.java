package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers")
public class Customers {

    @Id
    private String userName;
    @Column(name = "firstname")
    private String userFirstName;
    @Column(name = "lastname")
    private String userLastName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int points;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "Role",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "ROLE_ID")
//            }
//    )
//    private Set<Role> role;

    @JoinColumn(nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Role role;


}
