package com.cashviewer.infrastructure.loginandregister;


import com.cashviewer.domain.usercrud.UserFacade;
import com.cashviewer.domain.usercrud.dto.UserRegisterRequestDto;
import com.cashviewer.domain.usercrud.dto.UserRegisterResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
class LoginAndRegisterController {

    private final UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody @Valid UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto responseDto = userFacade.registerUser(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

}
