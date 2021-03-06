package INT222.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class Users {


    @Id
    private long id;
    @Column
    private String username;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name")
            }
    )
    private Set<Roles> role;

    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private List<CartItems> cartItems;

}
