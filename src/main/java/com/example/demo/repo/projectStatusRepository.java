package com.example.demo.repo;
import com.example.demo.models.projectStatus;
import org.springframework.data.repository.CrudRepository;

public interface projectStatusRepository extends CrudRepository<projectStatus, Long> {
    projectStatus findByNameProjectStatus(String nameProjectStatus);
}
