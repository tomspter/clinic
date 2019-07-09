package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog,Integer> {
}
