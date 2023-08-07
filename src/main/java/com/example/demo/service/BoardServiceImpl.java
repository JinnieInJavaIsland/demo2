package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDTO;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public Board update(BoardDTO boardDTO) {
        Long bno = boardDTO.getBno();
        String newTitle = boardDTO.getTitle();
        String newContent = boardDTO.getContent();

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change(newTitle, newContent);
        boardRepository.save(board);

        return board;
    }

    /*
          Long bno = 101L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update title 101", "update cotent 101");
        boardRepository.save(board);
     */

    @Override
    public void delete(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public Board read(Long bno) {

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
        return board;
    }

    @Override
    public List<Board> list() {
        List<Board> list = boardRepository.findAll();

        return list;
    }
}
