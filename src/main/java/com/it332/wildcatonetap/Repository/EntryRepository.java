package com.it332.wildcatonetap.Repository;

import com.it332.wildcatonetap.Entity.EntryEntity;
import com.it332.wildcatonetap.Entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Long> {
    List<EntryEntity> findByUser(UserEntity user);
}