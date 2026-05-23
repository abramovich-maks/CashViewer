package com.cashviewer.domain.loginandregister;

import com.cashviewer.domain.loginandregister.dto.UserRegisterRequestDto;
import com.cashviewer.domain.loginandregister.dto.UserRegisterResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Log4j2
@Component
class UserAdder {

    private final UserRepository userRepository;
    private final UserRetriever userRetriever;
    private final PasswordEncoder bCryptpasswordEncoder;
    private final UserEntityMapper userEntityMapper;

    UserRegisterResponseDto addUser(final UserRegisterRequestDto requestDto) {
        String email = requestDto.email().trim().toLowerCase();
        userRetriever.checkEmailExists(email);
        String encodedPassword = bCryptpasswordEncoder.encode(requestDto.password());
        UserEntity createdUser = UserEntity.builder().username(requestDto.username()).email(email).password(encodedPassword).build();
        UserEntity savedUser = userRepository.save(createdUser);
        log.info("Saved user with id: {}, email: {}", savedUser.getId(), savedUser.getEmail());
        return userEntityMapper.toUserRegisterResponseDto(savedUser);
    }
}

