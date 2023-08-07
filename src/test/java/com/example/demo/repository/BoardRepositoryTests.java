package com.example.demo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.domain.Board;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){ //데이터 등록
        IntStream.rangeClosed(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("title...." + i)
                    .content("content ... " + i)
                    .writer("user" + (i%10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testInsert2(){
        Board board = Board.builder()
                .title("제목얼마나긴지테스트제목얼마나긴지테스트제목얼마나긴지")
                .content("콘텐츠")
                .writer("유저")
                .build();

        boardRepository.save(board);
    }

    @Test
    public void testSelect(){ //데이터 읽기
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testUpdate(){ //데이터 수정
        Long bno = 101L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update title 101", "update cotent 101");
        boardRepository.save(board);
    }

    @Test
    public void testDelete(){ //데이터 삭제
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testList(){
        List<Board> list = boardRepository.findAll();
        for(Board board : list){
            log.info(board);
            log.info(board.getRegDate());
            log.info(board.getModDate());
        }
    }


}