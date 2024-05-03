package com.jia.auth.application.convert;

import com.jia.auth.application.dto.AuthUserDTO;
import com.jia.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertAuthUserDTOTOAuthUserBO(AuthUserDTO authUserDTO);
}
