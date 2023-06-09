package com.example.gimal.dao;

import com.example.gimal.dto.ChangeBoardDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.entity.Board;
import com.example.gimal.entity.Product;

import java.util.List;

public interface BoardDAO {
    Board selectBoard(Long id);
    Board insertBoard(Board board);
    Board updateBoard(String id, ChangeBoardDTO changeBoardDTO) throws  Exception;
    void deleteBoard(Long id) throws Exception;

    List<Board> selectAllBy();
    List<Board> selectAllByOrderByCreatedAtDESC();
    List<Board> selectAllByUid(String uid);
    List<Board> selectAllById(Long id);
}
