package com.example.gimal.service.Impl;

import com.example.gimal.dao.BoardDAO;
import com.example.gimal.dto.BoardDTO;
import com.example.gimal.dto.ChangeBoardDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.dto.UserDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.entity.Board;
import com.example.gimal.entity.Product;
import com.example.gimal.service.BoardService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDAO;
    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public BoardResponseDTO getOneBoard(Long id) {
        Board board = boardDAO.selectBoard(id);
        BoardResponseDTO boardResponseDTO =
            new BoardResponseDTO(board.getId(), board.getTitle(), board.getContents(), board.getUserId(), board.getUserName());
        return boardResponseDTO;
    }

    @Override
    public BoardResponseDTO changeBoard(String id, ChangeBoardDTO changeBoardDTO) throws Exception {
        Board changeBoard = boardDAO.updateBoard(id, changeBoardDTO);
        return new BoardResponseDTO(changeBoard);
    }

    @Override
    public BoardResponseDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContents(boardDTO.getContents());
        board.setUserId(boardDTO.getUserId());
        board.setUserName(boardDTO.getUserName());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board saveBoard = boardDAO.insertBoard(board);

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setId(saveBoard.getId());
        boardResponseDTO.setTitle(saveBoard.getTitle());
        boardResponseDTO.setContents(saveBoard.getContents());
        boardResponseDTO.setUserId(saveBoard.getUserId());
        boardResponseDTO.setUserName(saveBoard.getUserName());

        return boardResponseDTO;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        boardDAO.deleteBoard(id);
    }

    @Override
    public List<BoardResponseDTO> allBoard() {
        List<Board> products = boardDAO.selectAllBy();
        List<BoardResponseDTO> productResponseDTOS = products.stream().map(item ->
                new BoardResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

    @Override
    public List<BoardResponseDTO> allBoardOrderByCreatedAtDesc() {
        List<Board> products = boardDAO.selectAllByOrderByCreatedAtDESC();
        List<BoardResponseDTO> productResponseDTOS = products.stream().map(item ->
                new BoardResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

    @Override
    public List<BoardResponseDTO> allBoardById(String uid) {
        List<Board> products = boardDAO.selectAllByUid(uid);
        List<BoardResponseDTO> productResponseDTOS = products.stream().map(item ->
                new BoardResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

    @Override
    public List<BoardResponseDTO> allBoardByRealId(Long id) {
        List<Board> products = boardDAO.selectAllById(id);
        List<BoardResponseDTO> productResponseDTOS = products.stream().map(item ->
                new BoardResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

}
