package com.example.demo.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String  surname;
    private String name;
    private String patronymic;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Post post;
    @OneToMany(mappedBy = "employees")
    private Collection<cleaningScheduele> cleaningSchedueles;
    @OneToMany(mappedBy = "employees")
    private Collection<Equipment> equipment;
    @OneToMany(mappedBy = "employees")
    private Collection<copyrightCertificate> copyrightCertificates;
    @OneToMany(mappedBy = "employees")
    private Collection<researchReports> researchReports;

    public Employee(String surname, String name, String patronymic, Post post, Collection<cleaningScheduele> cleaningSchedueles, Collection<Equipment> equipment, Collection<copyrightCertificate> copyrightCertificates, Collection<researchReports> researchReports) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.post = post;
        this.cleaningSchedueles = cleaningSchedueles;
        this.equipment = equipment;
        this.copyrightCertificates = copyrightCertificates;
        this.researchReports = researchReports;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Collection<cleaningScheduele> getCleaningSchedueles() {
        return cleaningSchedueles;
    }

    public void setCleaningSchedueles(Collection<cleaningScheduele> cleaningSchedueles) {
        this.cleaningSchedueles = cleaningSchedueles;
    }

    public Collection<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Collection<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Collection<copyrightCertificate> getCopyrightCertificates() {
        return copyrightCertificates;
    }

    public void setCopyrightCertificates(Collection<copyrightCertificate> copyrightCertificates) {
        this.copyrightCertificates = copyrightCertificates;
    }

    public Collection<com.example.demo.models.researchReports> getResearchReports() {
        return researchReports;
    }

    public void setResearchReports(Collection<com.example.demo.models.researchReports> researchReports) {
        this.researchReports = researchReports;
    }
}
