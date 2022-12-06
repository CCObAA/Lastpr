package com.example.demo.repo;

import com.example.demo.models.researchType;
import org.springframework.data.repository.CrudRepository;

public interface researchTypeRepository extends CrudRepository <researchType,Long> {
    researchType findByNameResearchType(String nameResearchType);
}
