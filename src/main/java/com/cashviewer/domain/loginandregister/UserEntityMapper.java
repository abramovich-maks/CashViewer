package com.cashviewer.domain.loginandregister;

import com.cashviewer.domain.loginandregister.dto.UserRegisterResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
interface UserEntityMapper {
    UserEntity toEntity(UserRegisterResponseDto userRegisterResponseDto);

    UserRegisterResponseDto toUserRegisterResponseDto(UserEntity userEntity);
}