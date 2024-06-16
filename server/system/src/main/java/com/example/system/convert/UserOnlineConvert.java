package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.dal.vo.userOnline.UserOnlineDetailVO;
import com.example.system.dal.vo.userOnline.UserOnlineExportVO;
import com.example.system.dal.vo.userOnline.UserOnlineListVO;
import com.example.system.dal.vo.userOnline.UserOnlinePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserOnlineConvert {
    UserOnlineConvert INSTANCE = Mappers.getMapper(UserOnlineConvert.class);

    PageList<UserOnlinePageVO> page(PageList<UserOnlineEntity> userOnline);

    List<UserOnlineListVO> list(List<UserOnlineEntity> userOnline);

    UserOnlineDetailVO detail(UserOnlineEntity userOnline);

    UserOnlineEntity save(UserOnlineSaveDTO userOnline);

    List<UserOnlineEntity> saveList(List<UserOnlineSaveDTO> userOnline);

    List<UserOnlineExportVO> export(List<UserOnlineEntity> userOnline);

    List<UserOnlineSaveDTO> imports(List<UserOnlineExportVO> userOnline);
}