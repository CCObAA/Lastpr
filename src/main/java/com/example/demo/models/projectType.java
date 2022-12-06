package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class projectType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 6,max = 30,message = "От 6 до 30 символов")
    private String typeProject;
    @OneToMany(mappedBy = "projectType")
    private Collection<organizationProject> orgProjects;

    public projectType(String typeProject, Collection<organizationProject> orgProjects) {
        this.typeProject = typeProject;
        this.orgProjects = orgProjects;
    }
    public projectType() {

    }
    public Collection<organizationProject> getOrgProjects() {
        return orgProjects;
    }

    public void setOrgProjects(Collection<organizationProject> orgProjects) {
        this.orgProjects = orgProjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeProject() {
        return typeProject;
    }

    public void setTypeProject(String typeProject) {
        this.typeProject = typeProject;
    }
}
