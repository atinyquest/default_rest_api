package com.example.kdh.user;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.kdh.user.model.dto.UserRepository;
import com.example.kdh.user.model.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    static void setUp(@Autowired UserRepository userRepository) {
        // given
        userRepository.save(
                User.builder()
                        .name("John")
                        .email("John@blakcholic.com")
                        .build()
        );
    }

    @AfterAll
    static void tearDown(@Autowired UserRepository userRepository) {
        userRepository.deleteById(4L);
        userRepository.findAll().forEach(System.out::println);
    }

    @DisplayName("getAllUsers : 모든 사용자 조회에 성공")
    @Test
    void selectAll() throws Exception {

        String url = "/user/select-all";

        // when
        ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void selectUser() throws Exception {
        String url = "/user/select/1";

        // when
        ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void objectMapperTest() throws JsonProcessingException {
        User user = User.builder()
                .name("Jackson")
                .email("jackson@blackholic.com")
                .build();

        ObjectMapper om = new ObjectMapper();
        String jsonValue = om.writeValueAsString(user);
        System.out.println(jsonValue);
    }

    @Test
    void insert() throws Exception {
        // given
        String url = "/user/insert";
        User user = User.builder()
                .name("Jackson")
                .email("jackson@blackholic.com")
                .build();

        ObjectMapper om = new ObjectMapper();
        String jsonValue = om.writeValueAsString(user);

        // when
        ResultActions resultActions = mockMvc.perform(post(url)
                        .content(jsonValue)
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        // given
        String url = "/user/update";
        User user = User.builder()
                .seqId(2L)
                .name("Jackson")
                .email("jackson@blackholic.com")
                .build();

        ObjectMapper om = new ObjectMapper();
        String jsonValue = om.writeValueAsString(user);

        // when
        ResultActions resultActions = mockMvc.perform(put(url)
                .content(jsonValue)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteTest() throws Exception {
        // given
        String url = "/user/delete/2";

        // when
        ResultActions resultActions = mockMvc.perform(delete(url));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}