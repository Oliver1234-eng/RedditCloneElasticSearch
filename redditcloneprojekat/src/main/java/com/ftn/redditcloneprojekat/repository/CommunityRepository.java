package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, String> {

    @Query("select c from Community c left join fetch c.posts e where c.name =?1")
    public Community findOneWithPosts(String communityName);
}
