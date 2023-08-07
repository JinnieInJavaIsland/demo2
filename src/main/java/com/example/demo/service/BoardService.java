package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    Board update(BoardDTO boardDTO);

    void delete(Long bno);

    Board read(Long bno);

    List<Board> list();
}
