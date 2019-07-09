package com.clinicmaster.clinic.repository;

import com.clinicmaster.clinic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserNameAndPassWord(String userName,String passwd);
}
