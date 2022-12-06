package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.researchReports;
import com.example.demo.models.researchType;
import com.example.demo.models.organizationProject;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.researchTypeRepository;
import com.example.demo.repo.researchReportsRepository;
import com.example.demo.repo.organizationProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('STOCK','PROJECT','RESEARCHER','ADMIN')")
public class researchReportsController {
    @Autowired
    private researchReportsRepository researchReportsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private organizationProjectRepository organizationProjectRepository;
    @Autowired
    private researchTypeRepository researchTypeRepository;

    @GetMapping("/reports")
    @PreAuthorize("hasAnyAuthority('STOCK','PROJECT','RESEARCHER','ADMIN','USER')")
    public String reportsMain(Model model)
    {
        Iterable<researchReports> reports = researchReportsRepository.findAll();
        model.addAttribute("reports", reports);
        return "reports-main";
    }

    @GetMapping("/reports/add")
    public String reportsAdd(@ModelAttribute("reports") researchReports researchreports, Model addr)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        addr.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        addr.addAttribute("orgproj",orgproj);
        Iterable<researchType> researchtypes = researchTypeRepository.findAll();
        addr.addAttribute("researchtypes",researchtypes);
        return "reports-add";
    }

    @PostMapping("/reports/add")
    public String PostReportsAdd(@ModelAttribute("reports") @Valid researchReports researchreports, BindingResult bindingResult, @RequestParam String nameOrganization, @RequestParam String surname, @RequestParam String nameResearchType, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<Employee> employees = employeeRepository.findAll();
            addr.addAttribute("employees",employees);
            Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
            addr.addAttribute("orgproj",orgproj);
            Iterable<researchType> researchtypes = researchTypeRepository.findAll();
            addr.addAttribute("researchtypes",researchtypes);
            return "reports-add";
        }
        Iterable<Employee> employees = employeeRepository.findAll();
        addr.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        addr.addAttribute("orgproj",orgproj);
        Iterable<researchType> researchtypes = researchTypeRepository.findAll();
        addr.addAttribute("researchtypes",researchtypes);

        researchreports.setOrgProject(organizationProjectRepository.findByNameOrganization(nameOrganization));
        researchreports.setEmployees(employeeRepository.findBySurname(surname));
        researchreports.setResearchType(researchTypeRepository.findByNameResearchType(nameResearchType));

        researchReportsRepository.save(researchreports);
        return "redirect:/reports";
    }

    @PostMapping("/reports/filter")
    public String copyrightResult(@RequestParam(defaultValue = "") String nameResearch, Model model)
    {
        List<researchReports> result = researchReportsRepository.findByNameResearchContains(nameResearch);
        model.addAttribute("result", result);
        return "reports-main";
    }

    @GetMapping("/reports/{id}/edit")
    public  String copyrightDetails(@PathVariable(value = "id") long id, Model model)
    {
        researchReports reports = researchReportsRepository.findById(id).orElseThrow();
        model.addAttribute("reports", reports);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees",employees);
        Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
        model.addAttribute("orgproj",orgproj);
        Iterable<researchType> researchtypes = researchTypeRepository.findAll();
        model.addAttribute("researchtypes",researchtypes);

        return "reports-edit";
    }

    @PostMapping ("/reports/{id}/edit")
    public  String copyrightUpdate(@ModelAttribute("reports") @Valid researchReports researchreports, BindingResult bindingResult,
                                   @PathVariable(value = "id") long id, @RequestParam String surname, @RequestParam String nameOrganization, @RequestParam String nameResearchType, Model model)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employees",employees);
            Iterable<organizationProject> orgproj = organizationProjectRepository.findAll();
            model.addAttribute("orgproj",orgproj);
            Iterable<researchType> researchtypes = researchTypeRepository.findAll();
            model.addAttribute("researchtypes",researchtypes);
            return "reports-edit";
        }
        researchreports.setOrgProject(organizationProjectRepository.findByNameOrganization(nameOrganization));
        researchreports.setEmployees(employeeRepository.findBySurname(surname));
        researchreports.setResearchType(researchTypeRepository.findByNameResearchType(nameResearchType));
        researchReportsRepository.save(researchreports);
        return "redirect:/reports";
    }

    @GetMapping("/reports/{id}/remove")
    public String copyrightDelete(@PathVariable(value = "id") long id, Model model){
        researchReports reports = researchReportsRepository.findById(id).orElseThrow();
        researchReportsRepository.delete(reports);
        return "redirect:/reports";
    }
}
