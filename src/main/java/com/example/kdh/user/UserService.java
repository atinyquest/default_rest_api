package com.example.kdh.user;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
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

    public User save(User userReq) {
        userRepository.save(userReq);
        return userReq;
    }

    public User saveUser(User userReq) {
        userRepository.findById(userReq.getSeqId()).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        userRepository.save(userReq);
        return userReq;
    }

    public void deleteUser(Long seqId) {
        userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        userRepository.deleteById(seqId);
    }
}
