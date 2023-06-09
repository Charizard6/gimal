package com.example.gimal.repository;

import com.example.gimal.entity.Board;
import com.example.gimal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
    List<Board> findAllBy();
    List<Board> findAllByUserId(String uid);
    List<Board> findAllById(Long id);
}
