package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    @Query("select c from Community c join fetch c.posts e where c.id =?1")
    public Community findOneWithPosts(Integer communityId);
}
