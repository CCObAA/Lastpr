package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class researchType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 6,max = 25,message = "От 6 до 25 символов")
    private String nameResearchType;
    @OneToMany(mappedBy = "researchType")
    private Collection<researchReports> reports;

    public researchType(String nameResearchType, Collection<researchReports> reports) {
        this.nameResearchType = nameResearchType;
        this.reports = reports;
    }

    public researchType() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameResearchType() {
        return nameResearchType;
    }

    public void setNameResearchType(String nameResearchType) {
        this.nameResearchType = nameResearchType;
    }

    public Collection<researchReports> getReports() {
        return reports;
    }

    public void setReports(Collection<researchReports> reports) {
        this.reports = reports;
    }
}
