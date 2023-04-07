package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c join fetch c.reactions e where c.id =?1")
    public Comment findOneWithReactions(Integer commentId);

    @Query("select c from Comment c join fetch c.reports e where c.id =?1")
    public Comment findOneWithReports(Integer commentId);
}
