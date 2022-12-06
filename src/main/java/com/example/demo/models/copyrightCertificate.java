package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class copyrightCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4,max = 30,message = "От 4 до 30 символов")
    private String certificateNumber;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2,max = 30,message = "От 2 до 30 символов")
    private String nameSubject;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employees;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private organizationProject orgProd;

    public copyrightCertificate(String certificateNumber, String nameSubject, Employee employees, organizationProject orgProd) {
        this.certificateNumber = certificateNumber;
        this.nameSubject = nameSubject;
        this.employees = employees;
        this.orgProd = orgProd;
    }

    public copyrightCertificate() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public organizationProject getOrgProd() {
        return orgProd;
    }

    public void setOrgProd(organizationProject orgProd) {
        this.orgProd = orgProd;
    }
}
