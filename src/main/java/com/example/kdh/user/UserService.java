package com.example.kdh.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long seqId) {
        return userRepository.findById(seqId).orElse(null);
    }

    public void save(User userReq) {
        userRepository.save(userReq);
    }

    public void saveUser(User userReq) {
        userRepository.save(userReq);
    }

    public void deleteUser(Long seqId) {
        userRepository.deleteById(seqId);
    }
}
