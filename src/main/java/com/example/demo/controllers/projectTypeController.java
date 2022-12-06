package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.projectType;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.projectTypeRepository;
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
public class projectTypeController {
    @Autowired
    private projectTypeRepository projectTypeRepository;

    @GetMapping("/projecttype")
    public String projectTypeMain(Model model)
    {
        Iterable<projectType> project = projectTypeRepository.findAll();
        model.addAttribute("project", project);
        return "projecttype-main";
    }

    @GetMapping("/projecttype/add")
    public String projectTypeAdd(@ModelAttribute("project") projectType project, Model addr)
    {
        return "projecttype-add";
    }

    @PostMapping("/projecttype/add")
    public String PostProjectTypeAdd(@ModelAttribute("project") @Valid projectType project, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "projecttype-add";
        }

        projectTypeRepository.save(project);
        return "redirect:/projecttype";
    }

    @GetMapping("/projecttype/{id}/remove")
    public String projectTypeDelete(@PathVariable(value = "id") long id, Model model){
        projectType project = projectTypeRepository.findById(id).orElseThrow();
        projectTypeRepository.delete(project);
        return "redirect:/projecttype";
    }
}
