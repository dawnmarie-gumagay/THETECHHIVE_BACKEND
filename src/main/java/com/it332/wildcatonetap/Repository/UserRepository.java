package com.it332.wildcatonetap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.it332.wildcatonetap.Entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    UserEntity findByIdNumber(String idNumber);
    Optional<UserEntity> findByEmail(String email);
}
