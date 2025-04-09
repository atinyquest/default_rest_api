package com.example.kdh.user.repository;

import com.example.kdh.user.model.vo.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // select * from users where name = '?'
    Optional<User> findByName(String name);

    @Query("select u from User u where u.name =:name")
    Optional<User> jpqlFindByName(@Param("name") String name);

    @Query(value = "select * from users u where u.name =:name", nativeQuery = true)
    Optional<User> queryFindByName(@Param("name") String name);

    Optional<User> findByNameAndPassword(String name, String password);
}
