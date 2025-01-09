package com.example.kdh.user;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("select-all")
    public ApiResponse selectAll() {
        List<User> list = userService.findAll();
        if(CollectionUtils.isEmpty(list)){
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(list);
    }

    @GetMapping("select/{seqId}")
    public ApiResponse selectUser(@PathVariable Long seqId) {
        User user = userService.findById(seqId);
        if(user == null){
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(user);
    }

    @PostMapping("insert")
    public ApiResponse insert(@RequestBody User userReq) {
        userService.save(userReq);
        return ApiResponse.success(userReq);
    }

    @PutMapping("update")
    public ApiResponse update(@RequestBody User userReq) {
        userService.saveUser(userReq);
        return ApiResponse.success(userReq);
    }

    @DeleteMapping("delete/{seqId}")
    public ApiResponse delete(@PathVariable Long seqId) {
        userService.deleteUser(seqId);
        return ApiResponse.success();
    }
}
