package com.it332.wildcatonetap.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.wildcatonetap.Entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByPostId(int postId);
}