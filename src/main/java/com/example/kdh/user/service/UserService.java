package com.example.kdh.user.service;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.user.model.dto.UserReq;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.model.dto.UserRepository;
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

    public User saveUser(UserReq userReq) {
        if(userReq.getSeqId() != null && userReq.getSeqId() > 0){
            userRepository.findById(userReq.getSeqId()).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        }
        return userRepository.save(User.builder()
                .seqId(userReq.getSeqId())
                .name(userReq.getName())
                .email(userReq.getEmail())
                .build());
    }

    public void deleteUser(Long seqId) {
        userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        userRepository.deleteById(seqId);
    }
}
