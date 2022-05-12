
package ma.enset.patientmvc.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleid;
    @Column(unique = true)
    private String rolename;
    private String descriprion;
   /* @ManyToMany(mappedBy = "appRoles",fetch = FetchType.LAZY)
    private List<AppUser> appUsers=new ArrayList<>();*/
}
