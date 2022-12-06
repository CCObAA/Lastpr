package com.example.demo.repo;

import com.example.demo.models.researchReports;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface researchReportsRepository extends CrudRepository<researchReports,Long> {
    researchReports findByNameResearch(String nameResearch);
    List<researchReports> findByNameResearchContains(String nameResearch);
}
