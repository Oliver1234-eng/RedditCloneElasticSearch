package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.User;
import com.ftn.redditcloneprojekat.model.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select * from user where username = ?")
    public User findOneByUsername(String username);

    Optional<User> findFirstByUsername(String username);

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

    @Query("select u from User u left join fetch u.reactions e where u.username =?1")
    public User findOneWithReactions(String userUsername);

    @Query("select u from User u left join fetch u.reactionsOnPost e where u.username =?1")
    public User findOneWithReactionsOnPost(String userUsername);

    @Query("select u from User u left join fetch u.reports e where u.username =?1")
    public User findOneWithReports(String userUsername);

    @Query("select u from User u left join fetch u.comments e where u.username =?1")
    public User findOneWithComments(String userUsername);

    @Query("select u from User u left join fetch u.posts e where u.username =?1")
    public User findOneWithPosts(String userUsername);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select u from User u left join fetch u.communities e where u.username =?1")
    public User findOneWithCommunities(String userUsername);

    public List<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public List<User> findAllByEmail(String email);

    public List<User> findByUsernameAndPasswordAllIgnoringCase(String username, String password);

    @Query(nativeQuery = true, value = "select * from user where email = ?")
    public List<User> pronadjiKorisnikePoEmailu(String email);

}
