package com.cashviewer.domain.loginandregister;


import com.cashviewer.domain.loginandregister.dto.UserRegisterRequestDto;
import com.cashviewer.domain.loginandregister.dto.UserRegisterResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserAdder userAdder;

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        return userAdder.addUser(requestDto);
    }

}
