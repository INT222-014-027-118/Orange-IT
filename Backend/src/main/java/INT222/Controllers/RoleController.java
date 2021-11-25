package INT222.Controllers;

import INT222.Models.Roles;

import INT222.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    @PreAuthorize("hasRole('Admin')")
    public Roles createNewRole(@RequestBody Roles role) {
        return roleService.createNewRole(role);
    }
}
