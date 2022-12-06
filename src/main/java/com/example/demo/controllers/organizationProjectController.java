package com.example.demo.controllers;

import com.example.demo.models.projectType;
import com.example.demo.models.projectStatus;
import com.example.demo.models.organizationProject;
import com.example.demo.repo.projectTypeRepository;
import com.example.demo.repo.projectStatusRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','PROJECT')")
public class organizationProjectController {
    @Autowired
    private organizationProjectRepository organizationProjectRepository;
    @Autowired
    private projectTypeRepository projectTypeRepository;
    @Autowired
    private projectStatusRepository projectStatusRepository;
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','PROJECT')")
    @GetMapping("/orgproject")
    public String organizationProjectMain(Model model)
    {
        Iterable<organizationProject> orgproject = organizationProjectRepository.findAll();
        model.addAttribute("orgproject", orgproject);
        return "orgproject-main";
    }

    @GetMapping("/orgproject/add")
    public String organizationProjectAdd(@ModelAttribute("orgproject") organizationProject organizationproject, Model addr)
    {
        Iterable<projectType> projecttypes = projectTypeRepository.findAll();
        addr.addAttribute("projecttypes",projecttypes);
        Iterable<projectStatus> projectstatus = projectStatusRepository.findAll();
        addr.addAttribute("projectstatus",projectstatus);
        return "orgproject-add";
    }

    @PostMapping("/orgproject/add")
    public String PostOrganizationProjectAdd(@ModelAttribute("orgproject") @Valid organizationProject organizationproject, BindingResult bindingResult, @RequestParam String typeProject,@RequestParam String nameProjectStatus, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<projectType> projecttypes = projectTypeRepository.findAll();
            addr.addAttribute("projecttypes",projecttypes);
            Iterable<projectStatus> projectstatus = projectStatusRepository.findAll();
            addr.addAttribute("projectstatus",projectstatus);
            return "orgproject-add";
        }
        Iterable<projectType> projecttypes = projectTypeRepository.findAll();
        addr.addAttribute("projecttypes",projecttypes);
        Iterable<projectStatus> projectstatus = projectStatusRepository.findAll();
        addr.addAttribute("projectstatus",projectstatus);

        organizationproject.setProjectType(projectTypeRepository.findByTypeProject(typeProject));
        organizationproject.setProjectStatus(projectStatusRepository.findByNameProjectStatus(nameProjectStatus));

        organizationProjectRepository.save(organizationproject);
        return "redirect:/orgproject";
    }

    @PostMapping("/orgproject/filter")
    public String organizationProjectResult(@RequestParam(defaultValue = "") String nameOrganization, Model model)
    {
        List<organizationProject> result = organizationProjectRepository.findByNameOrganizationContains(nameOrganization);
        model.addAttribute("result", result);
        return "orgproject-main";
    }

    @GetMapping("/orgproject/{id}/edit")
    public  String organizationProjectDetails(@PathVariable(value = "id") long id, Model model)
    {
        organizationProject orgproject = organizationProjectRepository.findById(id).orElseThrow();
        model.addAttribute("orgproject", orgproject);

        Iterable<projectType> projecttypes = projectTypeRepository.findAll();
        model.addAttribute("projecttypes",projecttypes);
        Iterable<projectStatus> projectstatus = projectStatusRepository.findAll();
        model.addAttribute("projectstatus",projectstatus);

        return "orgproject-edit";
    }

    @PostMapping ("/orgproject/{id}/edit")
    public  String organizationProjectUpdate(@ModelAttribute("orgproject") @Valid organizationProject organizationproject, BindingResult bindingResult,
                                   @PathVariable(value = "id") long id, @RequestParam String typeProject, @RequestParam String nameProjectStatus, Model model)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<projectType> projecttypes = projectTypeRepository.findAll();
            model.addAttribute("projecttypes",projecttypes);
            Iterable<projectStatus> projectstatus = projectStatusRepository.findAll();
            model.addAttribute("projectstatus",projectstatus);
            return "orgproject-edit";
        }
        organizationproject.setProjectType(projectTypeRepository.findByTypeProject(typeProject));
        organizationproject.setProjectStatus(projectStatusRepository.findByNameProjectStatus(nameProjectStatus));
        organizationProjectRepository.save(organizationproject);
        return "redirect:/orgproject";
    }

    @GetMapping("/orgproject/{id}/remove")
    public String organizationProjectDelete(@PathVariable(value = "id") long id, Model model){
        organizationProject orgproject = organizationProjectRepository.findById(id).orElseThrow();
        organizationProjectRepository.delete(orgproject);
        return "redirect:/orgproject";
    }
}
