package ma.enset.patientmvc.security.services;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.patientmvc.security.entities.AppRole;
import ma.enset.patientmvc.security.entities.AppUser;
import ma.enset.patientmvc.security.repositorises.AppRoleRepository;
import ma.enset.patientmvc.security.repositorises.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword))throw new  RuntimeException(" the password is not matched ");
        String hashedpassword=passwordEncoder.encode(password);

        AppUser appUser= new AppUser();
        appUser.setUserid(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedpassword);
        appUser.setActive(true);
        AppUser savedappUser=appUserRepository.save(appUser);
        return savedappUser;
    }

    @Override
    public AppRole saveNewRole(String rolename, String desciption) {

        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole!=null)throw new  RuntimeException("Role"+rolename+"is already existe");
        appRole= new AppRole();
        appRole.setRolename(rolename);
        appRole.setDescriprion(desciption);
        AppRole savedaprole=appRoleRepository.save(appRole);
        return savedaprole;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new  RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole==null)throw new  RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null)throw new  RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole==null)throw new  RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);

    }

    @Override
    public AppUser laodByUUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
