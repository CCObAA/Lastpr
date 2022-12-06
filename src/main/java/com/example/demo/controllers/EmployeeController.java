package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.Post;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PostRepository postRepository;
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/employee")
    public String employeeMain(Model model)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee-main";
    }

    @GetMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employees") Employee employee, Model addr)
    {
        Iterable<Post> post = postRepository.findAll();
        addr.addAttribute("post",post);
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String PostEmployeeAdd(@ModelAttribute("employees") Employee employee, @RequestParam String namepost, Model addr)
    {
        Iterable<Post> post = postRepository.findAll();
        addr.addAttribute("post",post);
        employee.setPost(postRepository.findByNamepost(namepost));

        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/employee/filter")
    public String employeeResult(@RequestParam(defaultValue = "") String surname, Model model)
    {
        List<Employee> result = employeeRepository.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "employee-main";
    }

    @GetMapping("/employee/{id}/edit")
    public  String EmployeeDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Employee> post = employeeRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("employees",res);
        if(!employeeRepository.existsById(id))
        {
            return  "redirect:/employee";
        }
        return "employee-edit";
    }

    @PostMapping ("/employee/{id}/edit")
    public  String EmployeeUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam(defaultValue = "")  String surname,
                                  @RequestParam(defaultValue = "")  String name,
                                  @RequestParam(defaultValue = "")  String patronymic,
                                   Model model)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setSurname(surname);
        employee.setSurname(name);
        employee.setSurname(patronymic);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }


    @GetMapping("/employee/{id}/remove")
    public  String EmployeeDelDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Employee> post = employeeRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("employee",res);
        if(!employeeRepository.existsById(id))
        {
            return  "redirect:/employee";
        }
        return EmployeeDelete(id,model);
    }
    @PostMapping("/employee/{id}/remove")
    public String EmployeeDelete(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }

}
