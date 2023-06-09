package com.example.gimal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDTO extends SignUpResultDTO{
    private String token;

    @Builder
    public SignInResultDTO(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
