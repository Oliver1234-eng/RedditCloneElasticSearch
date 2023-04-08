package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findFirstByUsername(String username);

}
