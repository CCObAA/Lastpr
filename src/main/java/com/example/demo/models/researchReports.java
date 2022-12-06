package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class researchReports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 6,max = 25,message = "От 6 до 25 символов")
    private String nameResearch;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 20,max = 500,message = "От 20 до 500 символов")
    private String resultResearch;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employees;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private organizationProject orgProject;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private researchType researchType;
    @ManyToMany
    @JoinTable(name="resrep_equipment",
            joinColumns=@JoinColumn(name="researchReports_id"),
            inverseJoinColumns=@JoinColumn(name="equipment_id"))
    private List<Equipment> equipment;

    public researchReports(String nameResearch, String resultResearch, Employee employees, organizationProject orgProject, com.example.demo.models.researchType researchType, List<Equipment> equipment) {
        this.nameResearch = nameResearch;
        this.resultResearch = resultResearch;
        this.employees = employees;
        this.orgProject = orgProject;
        this.researchType = researchType;
        this.equipment = equipment;
    }

    public researchReports() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameResearch() {
        return nameResearch;
    }

    public void setNameResearch(String nameResearch) {
        this.nameResearch = nameResearch;
    }

    public String getResultResearch() {
        return resultResearch;
    }

    public void setResultResearch(String resultResearch) {
        this.resultResearch = resultResearch;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public organizationProject getOrgProject() {
        return orgProject;
    }

    public void setOrgProject(organizationProject orgProject) {
        this.orgProject = orgProject;
    }

    public com.example.demo.models.researchType getResearchType() {
        return researchType;
    }

    public void setResearchType(com.example.demo.models.researchType researchType) {
        this.researchType = researchType;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
