package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
}
