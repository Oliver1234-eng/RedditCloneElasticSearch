package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Flair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlairRepository extends JpaRepository<Flair, String> {

    @Query("select f from Flair f left join fetch f.posts e where f.name =?1")
    public Flair findOneWithPosts(String flairName);
}
