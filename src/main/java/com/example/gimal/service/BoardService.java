package com.example.gimal.service;

import com.example.gimal.dto.BoardDTO;
import com.example.gimal.dto.ChangeBoardDTO;
import com.example.gimal.dto.UserDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.dto.response.ProductResponseDTO;

import java.util.List;

public interface BoardService {
    BoardResponseDTO getOneBoard(Long id);
    BoardResponseDTO changeBoard(String id, ChangeBoardDTO changeBoardDTO) throws Exception;
    BoardResponseDTO createBoard(BoardDTO boardDTO);
    void deleteBoard(Long id) throws Exception;

    List<BoardResponseDTO> allBoard();
    List<BoardResponseDTO> allBoardOrderByCreatedAtDesc();

    List<BoardResponseDTO> allBoardById(String uid);
    List<BoardResponseDTO> allBoardByRealId(Long id);
}
