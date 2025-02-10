package com.example.kdh.user.service;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.user.model.dto.UserRequestDTO;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.repository.UserRepository;
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
        return userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
    }

    public User saveUser(UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getSeqId() != null && userRequestDTO.getSeqId() > 0){
            userRepository.findById(userRequestDTO.getSeqId()).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        }
        return userRepository.save(User.builder()
                .id(userRequestDTO.getSeqId())
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .build());
    }

    public void deleteUser(Long seqId) {
        userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        userRepository.deleteById(seqId);
    }
}
