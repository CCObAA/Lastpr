package com.example.demo.repo;

import com.example.demo.models.copyrightCertificate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface copyrightCertificateRepository extends CrudRepository<copyrightCertificate, Long> {
    List<copyrightCertificate> findByCertificateNumber(String certificateNumber);
}
