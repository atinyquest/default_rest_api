package com.example.kdh.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.kdh.user.model.vo.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void jpaQueryTest() {

        Optional<User> user = userRepository.findByName("user1");
        user.ifPresent(System.out::println);

        Optional<User> jpqlUser = userRepository.jpqlFindByName("user1");
        jpqlUser.ifPresent(System.out::println);

        Optional<User> queryUser = userRepository.queryFindByName("user1");
        queryUser.ifPresent(System.out::println);
    }
}