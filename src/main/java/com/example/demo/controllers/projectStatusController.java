package com.example.demo.controllers;

import com.example.demo.models.projectStatus;
import com.example.demo.repo.projectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','PROJECT')")
public class projectStatusController {
    @Autowired
    private projectStatusRepository projectStatusRepository;

    @GetMapping("/projectstatus")
    public String projectStatusMain(Model model)
    {
        Iterable<projectStatus> project = projectStatusRepository.findAll();
        model.addAttribute("project", project);
        return "projectstatus-main";
    }

    @GetMapping("/projectstatus/add")
    public String projectTypeAdd(@ModelAttribute("project") projectStatus project, Model addr)
    {
        return "projectstatus-add";
    }

    @PostMapping("/projectstatus/add")
    public String PostProjectStatusAdd(@ModelAttribute("project") @Valid projectStatus project, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "projectstatus-add";
        }

        projectStatusRepository.save(project);
        return "redirect:/projectstatus";
    }

    @GetMapping("/projectstatus/{id}/remove")
    public String projectStatusDelete(@PathVariable(value = "id") long id, Model model){
        projectStatus project = projectStatusRepository.findById(id).orElseThrow();
        projectStatusRepository.delete(project);
        return "redirect:/projectstatus";
    }
}
