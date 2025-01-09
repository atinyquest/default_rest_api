package com.example.kdh;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class DefaultRestApiApplicationTests {

    @BeforeAll
    static void beforeAll() {
        System.out.println("테스트 시작 전에 딱 한번만 실행");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("테스트 종료 후에 딱 한번만 실행");
    }

    @BeforeEach
    void setUp() {
        System.out.println("테스트 시작");
    }

    @AfterEach
    void tearDown() {
        System.out.println("테스트 종료");
    }

//    @DisplayName("간단 산술연산")
//    @Test
//    void mathTest() {
//        int a = 10;
//        int b = 20;
//        int sum = 30;
//
//        Assertions.assertEquals(sum, a + b);
//    }

    @DisplayName("다른 Assertions 산술 연산")
    @Test
    void basicTest() {
        int a = 10;
        int b = 20;
        int sum = 30;
        Assertions.assertThat(a+b).isEqualTo(sum);
        Assertions.assertThat(a).isEqualTo(b);
    }

    @DisplayName("리스트 data 테스트")
    @Test
    void listTest() {
        List<String> list  = List.of("John","Jane","Doe");

        Assertions.assertThat(list).hasSize(3)
                .contains("John")
                .contains("Jane")
                .contains("Doe")
                .contains("John","Jane")
                .doesNotContain("dahyeon")
                .containsExactly("John","Jane","Doe");
    }

    @Test
    void testException() {
        Throwable throwable = Assertions.catchThrowable(() -> {
            throw new IllegalArgumentException("Invalid Args");
        });

        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid");
    }
}
