package com.cashviewer.infrastructure.loginandregister;


import com.cashviewer.domain.loginandregister.LoginAndRegisterFacade;
import com.cashviewer.domain.loginandregister.dto.UserRegisterRequestDto;
import com.cashviewer.domain.loginandregister.dto.UserRegisterResponseDto;
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

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody @Valid UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto responseDto = loginAndRegisterFacade.registerUser(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

}
