package INT222.Controllers;



import INT222.Models.Users;
import INT222.Repositories.UserRepository;
import INT222.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @PostConstruct
    public void initRoleAndUser() {

        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public Users registerNewUser(@RequestBody Users user) {
        if(userRepository.findTopByOrderByIdDesc() ==  null){
            user.setId(1);
        }else
        user.setId(userRepository.findTopByOrderByIdDesc().getId()+1);
        return userService.registerNewUser(user);
    }

    @PostMapping({"/registerNewAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public Users registerNewAdmin(@RequestBody Users user) {
        if(userRepository.findTopByOrderByIdDesc() ==  null){
            user.setId(1);
        }else
            user.setId(userRepository.findTopByOrderByIdDesc().getId()+1);
        return userService.registerNewAdmin(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping({"/user/list"})
    @PreAuthorize("hasRole('Admin')")
    public List<Users> userList(){
        return userRepository.findAll();
    }

    @GetMapping({"/user/{id}"})
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public Optional<Users> getUserById(@PathVariable(value = "id") long id){
        return userRepository.findById(id);
    }

    @PutMapping({"/updateInfo"})
    @PreAuthorize("hasRole('User')" +
            " || hasRole('Admin')" )
    public void updateAccountInfo(@RequestBody Users users) {
        if (userRepository.existsById(users.getId()) && userRepository.existsByUsername(users.getUsername())) {
            userRepository.save(users);

        }
    }

    @DeleteMapping("removeUser/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable(value = "id") long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);

        }
    }
}
