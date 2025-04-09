package com.example.kdh.user.service;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.user.model.dto.UserRequestDTO;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long seqId) {
        return userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getSeqId() != null && userRequestDTO.getSeqId() > 0) {
            throw new CustomApiException(ApiResponseEnum.BAD_REQUEST); // 등록인데 ID가 있으면 에러
        }

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        return userRepository.save(User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(encodedPassword)
                .build());
    }

    public User updateUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getSeqId() == null || userRequestDTO.getSeqId() <= 0) {
            throw new CustomApiException(ApiResponseEnum.USER_NOT_FOUND);
        }

        User existingUser = userRepository.findById(userRequestDTO.getSeqId())
                .orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));

        existingUser.setName(userRequestDTO.getName());
        existingUser.setEmail(userRequestDTO.getEmail());

        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long seqId) {
        userRepository.findById(seqId).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        userRepository.deleteById(seqId);
    }
}
