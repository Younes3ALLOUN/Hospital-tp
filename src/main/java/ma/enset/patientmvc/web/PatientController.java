package ma.enset.patientmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.patientmvc.entities.Patient;
import ma.enset.patientmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
//******************************************************patients (index)
    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5")int size,
                           @RequestParam(name="keyword",defaultValue = "")String keyword){
        Page<Patient> pagepatients=patientRepository.findByNameContains(keyword,PageRequest.of(page, size));
        model.addAttribute("listpatients",pagepatients.getContent());
        model.addAttribute("pages",new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";

    }
//********************************************************************delete
    @GetMapping("/admin/delet")
    public String delete(Long id, String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
//**************************************************************home
    @GetMapping("/")
    public String home(){
        return "home";
    }
//****************************ù*************************** add patient
    @GetMapping("/admin/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
//******************************* **************************save(ajouter ou editer)
    @PostMapping(path="/admin/save")
    public String save(Model model, @Valid  Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()){
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
//************************************** ***************************edit
    @GetMapping("/admin/edit")
    public String edit(Model model,Long id,String keyword ,int page){
        Patient patient =  patientRepository.findById(id).orElse(null) ;
        if(patient== null)throw new RuntimeException("Patient n'existe pas");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);

        return "editor";
    }

}
