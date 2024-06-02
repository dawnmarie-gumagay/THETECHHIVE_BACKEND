package com.it332.wildcatonetap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it332.wildcatonetap.Entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
