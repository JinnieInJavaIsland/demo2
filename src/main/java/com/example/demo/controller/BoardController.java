package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;

    @ApiOperation(value = "Board POST", notes = "POST 방식으로 글 등록")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long register(@RequestBody BoardDTO boardDTO){
        Long bno = boardService.register(boardDTO);
        log.info(boardDTO);
        return bno;
    }

    @ApiOperation(value = "Board POST", notes = "GET 방식으로 리스트 조회")
    @GetMapping(value = "/list")
    public List<Board> list() {
        List<Board> boardList = boardService.list();
        return boardList;
    }

    @ApiOperation(value = "Board GET", notes = "GET 방식으로 게시물 단건 조회")
    @GetMapping(value = "/read")
    public Board readOne(Long bno) {
        Board board = boardService.read(bno);
        return board;
    }

    @ApiOperation(value = "Board POST", notes = "POST 방식으로 글 수정")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Board update(@RequestBody BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO,Board.class);
        boardService.update(boardDTO);
        log.info(board);
        return board;
    }

    @ApiOperation(value = "Board GET", notes = "GET 방식으로 게시물 삭제")
    @GetMapping(value = "/delete")
    public void remove(Long bno) {
        boardService.delete(bno);
    }





}
