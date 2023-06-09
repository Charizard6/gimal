package com.example.gimal.dao.Impl;

import com.example.gimal.dao.BoardDAO;
import com.example.gimal.dto.ChangeBoardDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.entity.Board;
import com.example.gimal.entity.Product;
import com.example.gimal.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board selectBoard(Long id) {
        Board selectBoard = boardRepository.getReferenceById(id);
        return selectBoard;
    }

    @Override
    public Board insertBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        return saveBoard;
    }

    @Override
    public Board updateBoard(String id, ChangeBoardDTO changeBoardDTO) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(changeBoardDTO.getId());

        Board updateBoard;
        if(selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            board.setTitle(changeBoardDTO.getTitle());
            board.setContents(changeBoardDTO.getContents());
            board.setUpdatedAt(LocalDateTime.now());
            updateBoard = boardRepository.save(board);
        } else throw new Exception();
        return updateBoard;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        Optional<Board> selectedProduct = boardRepository.findById(id);
        if(selectedProduct.isPresent()) {
            Board board = selectedProduct.get();
            boardRepository.delete(board);
        } else throw new Exception();
    }

    @Override
    public List<Board> selectAllBy() {
        return boardRepository.findAllBy();
    }

    @Override
    public List<Board> selectAllByOrderByCreatedAtDESC() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Board> selectAllByUid(String uid) {
        return boardRepository.findAllByUserId(uid);
    }

    @Override
    public List<Board> selectAllById(Long id) {
        return boardRepository.findAllById(id);
    }
}
