package ma.enset.patientmvc.security.repositorises;

import ma.enset.patientmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String rolename);
}
