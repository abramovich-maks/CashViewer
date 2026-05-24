package com.cashviewer.domain.usercrud;

import com.cashviewer.domain.usercrud.dto.UserRegisterResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
interface UserEntityMapper {
    UserEntity toEntity(UserRegisterResponseDto userRegisterResponseDto);

    UserRegisterResponseDto toUserRegisterResponseDto(UserEntity userEntity);
}