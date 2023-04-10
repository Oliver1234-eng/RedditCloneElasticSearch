package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join fetch p.reports e where p.id =?1")
    public Post findOneWithReports(Integer postId);

    @Query("select p from Post p join fetch p.comments e where p.id =?1")
    public Post findOneWithComments(Integer postId);

    @Query("select p from Post p join fetch p.reactions e where p.id =?1")
    public Post findOneWithReactions(Integer postId);

//    @Query("select p from Post p join fetch p.reactionsOnPost e where p.id =?1")
//    public Post findOneWithReactionsOnPost(Integer postId);

    @Query("select p from Post p left join fetch p.reactionsOnPost e where p.id = ?1")
    public Post findOneWithReactionsOnPost(Integer postId);
}
