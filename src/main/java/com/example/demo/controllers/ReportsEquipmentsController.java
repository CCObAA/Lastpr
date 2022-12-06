package com.example.demo.controllers;

import com.example.demo.models.Equipment;
import com.example.demo.models.projectType;
import com.example.demo.models.researchReports;
import com.example.demo.repo.EquipmentRepository;
import com.example.demo.repo.researchReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReportsEquipmentsController {
    @Autowired
    private researchReportsRepository researchReportsRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping("/reports/{research_reports_id}/{equipment_id}/remove")
    public  String humanDelete(@PathVariable(value = "research_reports_id") long research_reports_id,@PathVariable(value = "equipment_id") Long equipment_id, Model model)
    {
        researchReports reports = researchReportsRepository.findById(research_reports_id).orElseThrow();
        Equipment equipment = equipmentRepository.findById(equipment_id).orElseThrow();
        reports.getEquipment().remove(equipment);
        researchReportsRepository.save(reports);
        return "redirect:/reports";
    }

    @GetMapping("/reports/equipment/add")
    private String Main(Model model){
        Iterable<researchReports> reports = researchReportsRepository.findAll();
        model.addAttribute("reports", reports);
        Iterable<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        return "researchequipment-add";
    }

    @PostMapping("/reports/equipment/add")
    public String blogPostAdd(@RequestParam Long research, @RequestParam Long equipmen, Model model)
    {
        researchReports reports = researchReportsRepository.findById(research).orElseThrow();
        Equipment equipment = equipmentRepository.findById(equipmen).orElseThrow();
        reports.getEquipment().add(equipment);
        researchReportsRepository.save(reports);
        return "redirect:/reports";
    }
}
