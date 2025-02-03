package com.example.kdh.board.service;

import com.example.kdh.board.model.dto.BoardRepository;
import com.example.kdh.board.model.dto.BoardRequestDTO;
import com.example.kdh.board.model.vo.Board;
import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public List<Board> findAll() {
        List<Board> list = boardRepository.findAll();
        return list;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new CustomApiException(ApiResponseEnum.NO_CONTENT));
    }

    public void saveBoard(BoardRequestDTO boardRequestDTO) {
        if(boardRequestDTO.getId() == null || boardRequestDTO.getId() == 0) {
           Board board = modelMapper.map(boardRequestDTO, Board.class);
              boardRepository.save(board);
        }
        else {
            boardRepository.findById(boardRequestDTO.getId()).orElseThrow(() -> new CustomApiException(ApiResponseEnum.NO_CONTENT));
            Board board = modelMapper.map(boardRequestDTO, Board.class);
            boardRepository.save(board);
        }
    }

    public void deleteBoard(long id) {
        boardRepository.findById(id).orElseThrow(() -> new CustomApiException(ApiResponseEnum.NO_CONTENT));
        boardRepository.deleteById(id);
    }
}
