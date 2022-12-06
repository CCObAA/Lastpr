package com.example.demo.controllers;

import com.example.demo.models.cleaningScheduele;
import com.example.demo.models.Employee;
import com.example.demo.repo.cleaningSchedueleRepository;
import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','CLEANER')")
public class cleaningSchedueleController {
    @Autowired
    private cleaningSchedueleRepository cleaningSchedueleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/cleaning")
    public String cleaningMain(Model model)
    {
        Iterable<cleaningScheduele> cleaningSchedueles = cleaningSchedueleRepository.findAll();
        model.addAttribute("cleaningSchedueles", cleaningSchedueles);
        return "cleaning-main";
    }

    @GetMapping("/cleaning/add")
    public String cleaningAdd(@ModelAttribute("cleaning") cleaningScheduele cleaningScheduele, Model addr)
    {
        Iterable<Employee> employee = employeeRepository.findAll();
        addr.addAttribute("employee",employee);
        return "cleaning-add";
    }

    @PostMapping("/cleaning/add")
    public String PostCleaningAdd(@ModelAttribute("cleaning") cleaningScheduele cleaningScheduele, @RequestParam String surname, Model addr)
    {
        Iterable<Employee> employee = employeeRepository.findAll();
        addr.addAttribute("employee",employee);

        cleaningScheduele.setEmployees(employeeRepository.findBySurname(surname));

        cleaningSchedueleRepository.save(cleaningScheduele);
        return "redirect:/cleaning";
    }


    @GetMapping("/cleaning/{id}/remove")
    public  String cleaningDelDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<cleaningScheduele> clean = cleaningSchedueleRepository.findById(id);
        ArrayList<cleaningScheduele> res = new ArrayList<>();
        clean.ifPresent(res::add);
        model.addAttribute("cleaning",res);
        if(!employeeRepository.existsById(id))
        {
            return  "redirect:/cleaning";
        }
        return CleaningDelete(id,model);
    }
    @PostMapping("/cleaning/{id}/remove")
    public String CleaningDelete(@PathVariable(value = "id") long id, Model model){
        cleaningScheduele cleaningScheduele = cleaningSchedueleRepository.findById(id).orElseThrow();
        cleaningSchedueleRepository.delete(cleaningScheduele);
        return "redirect:/cleaning";
    }
}
