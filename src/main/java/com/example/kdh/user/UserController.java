package com.example.kdh.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("select-all")
    public List<User> selectAll() {
        return userService.findAll();
    }

    @GetMapping("select/{seqId}")
    public User selectUser(@PathVariable Long seqId) {
        return userService.findById(seqId);
    }

    @PostMapping("insert")
    public void insert(@RequestBody User userReq) {
        userService.save(userReq);
    }
}
