package com.example.gimal.dto.response;

import com.example.gimal.entity.Board;
import com.example.gimal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardResponseDTO {
    private long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;
    private Board board;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
    }

    public BoardResponseDTO(long id, String title, String contents, String userId, String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.userName = userName;
    }
}
