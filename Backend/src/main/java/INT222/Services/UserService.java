package INT222.Services;

import INT222.Models.Roles;
import INT222.Models.Users;
import INT222.Repositories.RoleRepository;
import INT222.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void initRoleAndUser() {


       if(!userRepository.existsByUsername("admin123")){
           Users adminUser = new Users();
           if(userRepository.findTopByOrderByIdDesc()== null){
               adminUser.setId(1);
               adminUser.setUsername("admin123");
               adminUser.setPassword(getEncodedPassword("admin@pass"));
               this.registerNewAdmin(adminUser);
           }else
               adminUser.setId(userRepository.findTopByOrderByIdDesc().getId()+1);
           adminUser.setUsername("admin123");
           adminUser.setPassword(getEncodedPassword("admin@pass"));
           this.registerNewAdmin(adminUser);
       }


//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public Users registerNewUser(Users user) {
        Roles role = roleRepository.findById("User").get();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public Users registerNewAdmin(Users user) {
        Roles role = roleRepository.findById("Admin").get();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
