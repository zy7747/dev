package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    PageList<UserPageVO> page(PageList<UserEntity> user);

    List<UserListVO> list(List<UserEntity> user);

    UserDetailVO detail(UserEntity user);

    UserEntity save(UserSaveDTO user);

    List<UserEntity> saveList(List<UserSaveDTO> user);

    List<UserExportVO> export(List<UserEntity> user);

    List<UserSaveDTO> imports(List<UserExportVO> user);

    UserBaseInfoVO UserBaseInfoVO(UserEntity users);
}