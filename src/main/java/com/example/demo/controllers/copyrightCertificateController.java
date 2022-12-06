package com.example.demo.controllers;

import com.example.demo.models.copyrightCertificate;
import com.example.demo.models.Employee;
import com.example.demo.models.organizationProject;
import com.example.demo.repo.copyrightCertificateRepository;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.organizationProjectRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','RESEARCHER')")
public class copyrightCertificateController {
    @Autowired
    private copyrightCertificateRepository copyrightCertificateRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private organizationProjectRepository organizationProjectRepository;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','RESEARCHER')")
    @GetMapping("/copyright")
    public String copyrightMain(Model model)
    {
        Iterable<copyrightCertificate> copyright = copyrightCertificateRepository.findAll();
        model.addAttribute("copyright", copyright);
        return "copyright-main";
    }

    @GetMapping("/copyright/add")
    public String copyrightAdd(@ModelAttribute("copyright") copyrightCertificate copyrightCertificate, Model addr)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        addr.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        addr.addAttribute("orgproj",orgproj);
        return "copyright-add";
    }

    @PostMapping("/copyright/add")
    public String PostcopyrightAdd(@ModelAttribute("copyright") @Valid copyrightCertificate copyrightcertificate, BindingResult bindingResult, @RequestParam String nameOrganization, @RequestParam String surname, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<Employee> employees = employeeRepository.findAll();
            addr.addAttribute("employees",employees);
            Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
            addr.addAttribute("orgproj",orgproj);
            return "copyright-add";
        }
        Iterable<Employee> employees = employeeRepository.findAll();
        addr.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        addr.addAttribute("orgproj",orgproj);

        copyrightcertificate.setOrgProd(organizationProjectRepository.findByNameOrganization(nameOrganization));
        copyrightcertificate.setEmployees(employeeRepository.findBySurname(surname));

        copyrightCertificateRepository.save(copyrightcertificate);
        return "redirect:/copyright";
    }

    @PostMapping("/copyright/filter")
    public String copyrightResult(@RequestParam(defaultValue = "") String certificateNumber, Model model)
    {
        List<copyrightCertificate> result = copyrightCertificateRepository.findByCertificateNumber(certificateNumber);
        model.addAttribute("result", result);
        return "copyright-main";
    }

    @GetMapping("/copyright/{id}/edit")
    public  String copyrightDetails(@PathVariable(value = "id") long id, Model model)
    {
        copyrightCertificate copyright = copyrightCertificateRepository.findById(id).orElseThrow();
        model.addAttribute("copyright", copyright);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        model.addAttribute("orgproj",orgproj);

        return "copyright-edit";
    }

    @PostMapping ("/copyright/{id}/edit")
    public  String copyrightUpdate(@ModelAttribute("copyright") @Valid copyrightCertificate copyrightcertificate, BindingResult bindingResult,
                                   @PathVariable(value = "id") long id, @RequestParam String surname, @RequestParam String nameOrganization, Model model)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees",employees);
            Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
            model.addAttribute("orgproj",orgproj);
            return "copyright-edit";
        }
        copyrightcertificate.setOrgProd(organizationProjectRepository.findByNameOrganization(nameOrganization));
        copyrightcertificate.setEmployees(employeeRepository.findBySurname(surname));
        copyrightCertificateRepository.save(copyrightcertificate);
        return "redirect:/copyright";
    }

    @GetMapping("/copyright/{id}/remove")
    public String copyrightDelete(@PathVariable(value = "id") long id, Model model){
        copyrightCertificate copyright = copyrightCertificateRepository.findById(id).orElseThrow();
        copyrightCertificateRepository.delete(copyright);
        return "redirect:/copyright";
    }
}
