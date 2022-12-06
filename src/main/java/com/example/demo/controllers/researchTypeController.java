package com.example.demo.controllers;

import com.example.demo.models.researchType;
import com.example.demo.repo.researchTypeRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class researchTypeController {
    @Autowired
    private researchTypeRepository researchTypeRepository;

    @GetMapping("/researchtype")
    public String researchTypeMain(Model model)
    {
        Iterable<researchType> project = researchTypeRepository.findAll();
        model.addAttribute("project", project);
        return "researchtype-main";
    }

    @GetMapping("/researchtype/add")
    public String researchTypeAdd(@ModelAttribute("project") researchType project, Model addr)
    {
        return "researchtype-add";
    }

    @PostMapping("/researchtype/add")
    public String PostResearchTypeAdd(@ModelAttribute("project") @Valid researchType project, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "researchtype-add";
        }

        researchTypeRepository.save(project);
        return "redirect:/researchtype";
    }

    @GetMapping("/researchtype/{id}/remove")
    public String researchTypeDelete(@PathVariable(value = "id") long id, Model model){
        researchType project = researchTypeRepository.findById(id).orElseThrow();
        researchTypeRepository.delete(project);
        return "redirect:/researchtype";
    }
}
