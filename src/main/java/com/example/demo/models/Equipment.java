package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity

public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(value = 0, message = "Значение не должно быть меньше 0")
    @Max(value = 300, message = "Значение не должно быть больше 300")
    private int quantities;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2,max = 30,message = "От 2 до 30 символов")
    private String nameEquipment;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employees;
    @ManyToMany(mappedBy = "equipment")
    private List<researchReports> researchReports;

    public Equipment(int quantities, String nameEquipment, Employee employees) {
        this.quantities = quantities;
        this.nameEquipment = nameEquipment;
        this.employees = employees;
    }

    public Equipment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public Employee getEmployee() {
        return employees;
    }

    public void setEmployee(Employee employees) {
        this.employees = employees;
    }
}
