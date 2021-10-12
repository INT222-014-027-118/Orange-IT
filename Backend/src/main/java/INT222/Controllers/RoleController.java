package INT222.Controllers;

import INT222.Models.Roles;

import INT222.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Roles createNewRole(@RequestBody Roles role) {
        return roleService.createNewRole(role);
    }
}
