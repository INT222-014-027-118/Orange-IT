package INT222.Services;

import INT222.Exceptions.SameRoleException;
import INT222.Models.Roles;
import INT222.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Roles createNewRole(Roles role) {
        if(roleRepository.existsById(role.getName())) {
            throw new SameRoleException(role.getName());
        }else
        return roleRepository.save(role);
    }

}
