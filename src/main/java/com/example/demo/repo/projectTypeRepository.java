package com.example.demo.repo;

import com.example.demo.models.projectType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface projectTypeRepository extends CrudRepository<projectType,Long> {
    projectType findByTypeProject(String typeProject);
}
