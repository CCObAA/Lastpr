package com.example.demo.repo;

import com.example.demo.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findBySurname(String surname);
    List<Employee> findBySurnameContains(String surname);

}
