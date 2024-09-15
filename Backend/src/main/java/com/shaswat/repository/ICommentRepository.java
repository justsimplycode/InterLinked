package com.shaswat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaswat.models.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer>{

}
