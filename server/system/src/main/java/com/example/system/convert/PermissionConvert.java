package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.permission.PermissionSaveDTO;
import com.example.system.dal.entity.PermissionEntity;
import com.example.system.dal.vo.permission.PermissionDetailVO;
import com.example.system.dal.vo.permission.PermissionExportVO;
import com.example.system.dal.vo.permission.PermissionListVO;
import com.example.system.dal.vo.permission.PermissionPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionConvert {
    PermissionConvert INSTANCE = Mappers.getMapper(PermissionConvert.class);

    PageList<PermissionPageVO> page(PageList<PermissionEntity> permission);

    List<PermissionListVO> list(List<PermissionEntity> permission);

    PermissionDetailVO detail(PermissionEntity permission);

    PermissionEntity save(PermissionSaveDTO permission);

    List<PermissionEntity> saveList(List<PermissionSaveDTO> permission);

    List<PermissionExportVO> export(List<PermissionEntity> permission);

    List<PermissionSaveDTO> imports(List<PermissionExportVO> permission);
}