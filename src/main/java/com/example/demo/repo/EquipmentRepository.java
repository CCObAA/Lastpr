package com.example.demo.repo;

import com.example.demo.models.Employee;
import com.example.demo.models.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepository extends  CrudRepository<Equipment, Long> {
    Equipment findByNameEquipment(String nameEquipment);
    List<Equipment> findByNameEquipmentContains(String nameEquipment);
}