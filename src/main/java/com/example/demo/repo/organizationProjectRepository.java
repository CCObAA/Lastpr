package com.example.demo.repo;

import com.example.demo.models.organizationProject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface organizationProjectRepository extends CrudRepository<organizationProject, Long> {
    organizationProject findByNameOrganization(String nameOrganization);

    List<organizationProject> findByNameOrganizationContains(String nameOrganization);
}