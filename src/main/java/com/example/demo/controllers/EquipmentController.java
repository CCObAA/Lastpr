package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.Equipment;
import com.example.demo.models.Post;
import com.example.demo.models.cleaningScheduele;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.EquipmentRepository;
import com.example.demo.repo.cleaningSchedueleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('STOCK','ADMIN')")
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PreAuthorize("hasAnyAuthority('STOCK','ADMIN','USER')")
    @GetMapping("/equipment")
    public String equipmentMain(Model model)
    {
        Iterable<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        return "equipment-main";
    }

    @GetMapping("/equipment/add")
    public String equipmentAdd(@ModelAttribute("equipment") Equipment equipment, Model addr)
    {
        Iterable<Employee> employee = employeeRepository.findAll();
        addr.addAttribute("employee",employee);
        return "equipment-add";
    }

    @PostMapping("/equipment/add")
    public String PostEquipmentAdd(@ModelAttribute("equipment") @Valid Equipment equipment, BindingResult bindingResult, @RequestParam String surname, Model addr)
    {
        if (bindingResult.hasErrors()) {
            Iterable<Employee> employee = employeeRepository.findAll();
            addr.addAttribute("employee",employee);
            return "equipment-add";
        }

        Iterable<Employee> employee = employeeRepository.findAll();
        addr.addAttribute("employee",employee);

        equipment.setEmployee(employeeRepository.findBySurname(surname));

        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

    @PostMapping("/equipment/filter")
    public String equipmentResult(@RequestParam(defaultValue = "") String nameEquipment, Model model)
    {
        List<Equipment> result = equipmentRepository.findByNameEquipmentContains(nameEquipment);
        model.addAttribute("result", result);
        return "equipment-main";
    }

    @GetMapping("/equipment/{id}/edit")
    public  String equipmentDetails(@PathVariable(value = "id") long id, Model model)
    {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        model.addAttribute("equipment", equipment);
        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee",employee);
        return "equipment-edit";
    }

    @PostMapping ("/equipment/{id}/edit")
    public  String equipmentUpdate(@ModelAttribute("equipment") @Valid Equipment equipment, BindingResult bindingResult,
                                   @PathVariable(value = "id") long id, @RequestParam String surname, Model mode)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<Employee> employee = employeeRepository.findAll();
            mode.addAttribute("employee",employee);
            return "equipment-edit";
        }
        equipment.setEmployee(employeeRepository.findBySurname(surname));
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

    @GetMapping("/equipment/{id}/remove")
    public String equipmentDelete(@PathVariable(value = "id") long id, Model model){
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        equipmentRepository.delete(equipment);
        return "redirect:/equipment";
    }
}
