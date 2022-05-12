package ma.enset.patientmvc.security.services;

import ma.enset.patientmvc.security.entities.AppRole;
import ma.enset.patientmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String rolename, String desciption);
    void addRoleToUser(String username,String rolename);
    void removeRoleFromUser(String username,String rolename);
    AppUser laodByUUsername(String username);

}
