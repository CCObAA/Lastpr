package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class organizationProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 4,max = 30,message = "От 4 до 30 символов")
    private String nameOrganization;

    @Min(value = 0, message = "Значение не должно быть меньше 0")
    @Max(value = 100000000, message = "Значение не должно быть больше 100000000")
    private double salaryProject;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4,max = 30,message = "От 4 до 30 символов")
    private String  regionalLocation;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 5,max = 300,message = "От 5 до 300 символов")
    private String projectDescription;

    @OneToMany(mappedBy = "orgProd")
    private Collection<copyrightCertificate> certificates;
    @OneToMany(mappedBy = "orgProject")
    private Collection<researchReports> reports;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private projectType projectType;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private projectStatus projectStatus;

    public organizationProject(String nameOrganization, double salaryProject, String regionalLocation, String projectDescription, Collection<copyrightCertificate> certificates, Collection<researchReports> reports, com.example.demo.models.projectType projectType, com.example.demo.models.projectStatus projectStatus) {
        this.nameOrganization = nameOrganization;
        this.salaryProject = salaryProject;
        this.regionalLocation = regionalLocation;
        this.projectDescription = projectDescription;
        this.certificates = certificates;
        this.reports = reports;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
    }

    public organizationProject(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public double getSalaryProject() {
        return salaryProject;
    }

    public void setSalaryProject(double salaryProject) {
        this.salaryProject = salaryProject;
    }

    public String getRegionalLocation() {
        return regionalLocation;
    }

    public void setRegionalLocation(String regionalLocation) {
        this.regionalLocation = regionalLocation;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Collection<copyrightCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Collection<copyrightCertificate> certificates) {
        this.certificates = certificates;
    }

    public Collection<researchReports> getReports() {
        return reports;
    }

    public void setReports(Collection<researchReports> reports) {
        this.reports = reports;
    }

    public com.example.demo.models.projectType getProjectType() {
        return projectType;
    }

    public void setProjectType(com.example.demo.models.projectType projectType) {
        this.projectType = projectType;
    }

    public com.example.demo.models.projectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(com.example.demo.models.projectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
