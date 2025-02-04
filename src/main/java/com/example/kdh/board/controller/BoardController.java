package com.example.kdh.board.controller;

import com.example.kdh.board.model.dto.BoardRequestDTO;
import com.example.kdh.board.model.vo.Board;
import com.example.kdh.board.service.BoardService;
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
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ApiResponse getBoardList() {
        List<Board> list = boardService.findAll();
        if(CollectionUtils.isEmpty(list)) {
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse getBoard(@PathVariable long id) {
        Board board = boardService.findById(id);
        if(board == null) {
            return ApiResponse.customError(new CustomApiException(ApiResponseEnum.NO_CONTENT));
        }
        return ApiResponse.success(board);
    }

    @PostMapping("/create")
    public ApiResponse createBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        boardService.saveBoard(boardRequestDTO);
        return ApiResponse.success();
    }

    @PutMapping("/update")
    public ApiResponse updateBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        boardService.saveBoard(boardRequestDTO);
        return ApiResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
        return ApiResponse.success();
    }
}
