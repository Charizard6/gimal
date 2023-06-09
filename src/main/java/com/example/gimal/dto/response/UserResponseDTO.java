package com.example.gimal.dto.response;

import com.example.gimal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String uid;
    private String name;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
