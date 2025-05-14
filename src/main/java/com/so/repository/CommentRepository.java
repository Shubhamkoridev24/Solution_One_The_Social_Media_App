package com.so.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.so.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	
	
	
}
