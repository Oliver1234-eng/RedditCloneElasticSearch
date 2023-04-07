package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Flair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlairRepository extends JpaRepository<Flair, Integer> {

    @Query("select f from Flair f join fetch f.posts e where f.id =?1")
    public Flair findOneWithPosts(Integer flairId);
}
