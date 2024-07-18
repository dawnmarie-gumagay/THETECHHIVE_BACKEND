package com.it332.wildcatonetap.Repository;

import com.it332.wildcatonetap.Entity.ProfileEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    ProfileEntity findByUser(UserEntity user);
}
