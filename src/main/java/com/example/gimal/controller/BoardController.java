package com.example.gimal.controller;

import com.example.gimal.config.security.JwtTokenProvider;
import com.example.gimal.dto.BoardDTO;
import com.example.gimal.dto.ChangeBoardDTO;
import com.example.gimal.dto.UserDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.dto.response.OrderResponseDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.dto.response.UserResponseDTO;
import com.example.gimal.entity.User;
import com.example.gimal.service.BoardService;
import com.example.gimal.service.UserDetailService;
import com.example.gimal.service.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public BoardController(BoardService boardService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.boardService = boardService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDTO> changeBoard(HttpServletRequest request,@RequestBody ChangeBoardDTO changeBoardDTO) throws Exception{
        BoardResponseDTO takenBoard = boardService.getOneBoard(changeBoardDTO.getId());
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(takenBoard.getUserId().equals(id)) {
            BoardResponseDTO boardResponseDTO =
                boardService.changeBoard(id, changeBoardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
        }
        return null;
    }
    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDTO> createBoard(HttpServletRequest request, @RequestParam String title,
        @RequestParam String contents) {
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        BoardDTO selectDTO = new BoardDTO();
        selectDTO.setTitle(title);
        selectDTO.setContents(contents);
        selectDTO.setUserId(userResponseDTO.getUid());
        selectDTO.setUserName(userResponseDTO.getName());
        BoardResponseDTO boardResponseDTO = boardService.createBoard(selectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }
    
    //삭제도 userid name 받아서 삭제하기
    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> deleteBoard(HttpServletRequest request,Long id) throws Exception {
        BoardResponseDTO boardResponseDTO = boardService.getOneBoard(id);
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(boardResponseDTO.getUserId().equals(userId)) {
            boardService.deleteBoard(id);
            return ResponseEntity.status(HttpStatus.OK).body("삭제됨");
        }
        return ResponseEntity.status(HttpStatus.OK).body("글을 작성한 유저가 아니다");
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDTO>> getAllBoard() {
        List<BoardResponseDTO> productResponseDTOS = boardService.allBoard();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOS);
    }
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDTO>> getAllBoardOrderByCreatedAt() {
        List<BoardResponseDTO> productResponseDTOS = boardService.allBoardOrderByCreatedAtDesc();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOS);
    }
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDTO>> getAllBoardByUid(String uid) {
        List<BoardResponseDTO> productResponseDTOS = boardService.allBoardById(uid);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOS);
    }
    
    //하나 가져오는거임
    @GetMapping()
    public ResponseEntity<BoardResponseDTO> getBoardById(long Id) {
        BoardResponseDTO boardResponseDTO = boardService.getOneBoard(Id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }
}
