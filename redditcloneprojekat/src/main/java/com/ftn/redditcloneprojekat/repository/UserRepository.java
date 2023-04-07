package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findOneByUsername(String username);

//    @Query("select u from User u join fetch u.reactions e where u.id =?1")
//    public User findOneWithReactions(Integer userId);
//
//    @Query("select u from User u join fetch u.reports e where u.id =?1")
//    public User findOneWithReports(Integer userId);
//
//    @Query("select u from User u join fetch u.comments e where u.id =?1")
//    public User findOneWithComments(Integer userId);
//
//    @Query("select u from User u join fetch u.posts e where u.id =?1")
//    public User findOneWithPosts(Integer userId);
//
//    @Query("select u from User u join fetch u.communities e where u.id =?1")
//    public User findOneWithCommunities(Integer userId);

    @Query("select u from User u join fetch u.reactions e where u.username =?1")
    public User findOneWithReactions(String userUsername);

    @Query("select u from User u join fetch u.reports e where u.username =?1")
    public User findOneWithReports(String userUsername);

    @Query("select u from User u join fetch u.comments e where u.username =?1")
    public User findOneWithComments(String userUsername);

    @Query("select u from User u join fetch u.posts e where u.username =?1")
    public User findOneWithPosts(String userUsername);

    @Query("select u from User u join fetch u.communities e where u.username =?1")
    public User findOneWithCommunities(String userUsername);

    public List<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public List<User> findAllByEmail(String email);

    public List<User> findByUsernameAndPasswordAllIgnoringCase(String username, String password);

    @Query(nativeQuery = true, value = "select * from user where email = ?")
    public List<User> pronadjiKorisnikePoEmailu(String email);

}
