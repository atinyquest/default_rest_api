package com.example.kdh.board.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardRequestDTO {

    private Long id;

    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;
}
