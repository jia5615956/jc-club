package com.jia.auth.domain.convert;


import com.jia.auth.domain.entity.AuthUserBO;
import com.jia.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBOConvert {
    AuthUserBOConvert INSTANCE = Mappers.getMapper(AuthUserBOConvert.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);


}
