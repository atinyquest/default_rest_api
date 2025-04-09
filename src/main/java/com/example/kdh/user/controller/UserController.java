package com.example.kdh.user.controller;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.common.model.CustomUserDetails;
import com.example.kdh.common.response.ApiResponse;
import com.example.kdh.user.model.dto.UserRequestDTO;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name="UserController", description = "사용자 관련 RestApi 리스트")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @GetMapping("/select-all")
    @Operation(summary = "사용자 정보 전체 조회")
    public ApiResponse selectAll() {
        List<User> list = userService.findAll();
        if(CollectionUtils.isEmpty(list)){
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(list);
    }

    @GetMapping("/select/{seqId}")
    @Operation(summary = "사용자 정보 조회")
    public ApiResponse selectUser(@PathVariable Long seqId) {
        User user = userService.findById(seqId);
        if(user == null){
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(user);
    }

    @PostMapping("/create")
    @Operation(summary = "사용자 정보 추가")
    public ApiResponse createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO);
        return ApiResponse.success(user);
    }

    @PutMapping("/update")
    @Operation(summary = "사용자 정보 수정")
    public ApiResponse updateUser(@RequestBody UserRequestDTO userRequestDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {

        if(userRequestDTO.getSeqId() == null || userRequestDTO.getSeqId() <= 0){
            userRequestDTO.setSeqId(userDetails.getId());
        }
        User user = userService.updateUser(userRequestDTO);
        return ApiResponse.success(user);
    }

    @DeleteMapping("/delete/{seqId}")
    @Operation(summary = "사용자 정보 삭제(delete)")
    public ApiResponse deleteUser(@PathVariable Long seqId) {
        userService.deleteUser(seqId);
        return ApiResponse.success();
    }
}
