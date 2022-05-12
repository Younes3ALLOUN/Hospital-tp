package ma.enset.patientmvc;

import ma.enset.patientmvc.entities.Patient;
import ma.enset.patientmvc.repositories.PatientRepository;
import ma.enset.patientmvc.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {


            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getId());
            });

        };
    }
    @Bean
    CommandLineRunner saveUser(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("amine","1234","1234");
            securityService.saveNewUser("jamila","1234","1234");
            securityService.saveNewUser("hasan","1234","1234");
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("amine","USER");
            securityService.addRoleToUser("amine","ADMIN");
            securityService.addRoleToUser("jamila","USER");
            securityService.addRoleToUser("hasan","USER");
        };
    }

}
