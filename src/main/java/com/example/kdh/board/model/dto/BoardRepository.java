package com.example.kdh.board.model.dto;

import com.example.kdh.board.model.vo.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
