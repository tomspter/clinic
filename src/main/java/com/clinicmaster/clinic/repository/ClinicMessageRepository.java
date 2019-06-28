package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.ClinicInfo;
import com.clinicmaster.clinic.domain.ClinicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicMessageRepository extends JpaRepository<ClinicInfo, Long> {
    @Override
    List<ClinicInfo> findAll();

    ClinicInfo findClinicByName(String name);
}
