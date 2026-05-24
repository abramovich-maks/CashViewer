package com.cashviewer.domain.usercrud;


import com.cashviewer.domain.usercrud.dto.UserDtoResponse;
import com.cashviewer.domain.usercrud.dto.UserRegisterRequestDto;
import com.cashviewer.domain.usercrud.dto.UserRegisterResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserFacade {

    private final UserAdder userAdder;
    private final UserRetriever userRetriever;

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        return userAdder.addUser(requestDto);
    }

    public Optional<UserDtoResponse> findByEmail(String email) {
        return userRetriever.findByEmail(email);
    }

    public boolean userExists(final String email) {
        return userRetriever.userExists(email);
    }
}
