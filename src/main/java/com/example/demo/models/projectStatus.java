package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class projectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 6,max = 25,message = "От 6 до 25 символов")
    private String nameProjectStatus;
    @OneToMany(mappedBy = "projectStatus")
    private Collection<organizationProject> orgProjects;

    public projectStatus(String nameProjectStatus, Collection<organizationProject> orgProjects) {
        this.nameProjectStatus = nameProjectStatus;
        this.orgProjects = orgProjects;
    }

    public projectStatus() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProjectStatus() {
        return nameProjectStatus;
    }

    public void setNameProjectStatus(String nameProjectStatus) {
        this.nameProjectStatus = nameProjectStatus;
    }

    public Collection<organizationProject> getOrgProjects() {
        return orgProjects;
    }

    public void setOrgProjects(Collection<organizationProject> orgProjects) {
        this.orgProjects = orgProjects;
    }
}
