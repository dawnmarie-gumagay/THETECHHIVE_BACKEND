package com.capstone.Corbete.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.Corbete.Entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
