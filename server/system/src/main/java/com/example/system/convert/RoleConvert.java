package com.example.system.convert;
import com.example.framework.common.PageList;
import com.example.system.dal.dto.role.RoleSaveDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.vo.role.RoleDetailVO;
import com.example.system.dal.vo.role.RoleExportVO;
import com.example.system.dal.vo.role.RoleListVO;
import com.example.system.dal.vo.role.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);
    PageList<RolePageVO> page(PageList<RoleEntity> role);
    List<RoleListVO> list(List<RoleEntity> role);
    RoleDetailVO detail(RoleEntity role);
    RoleEntity save(RoleSaveDTO role);
    List<RoleEntity> saveList(List<RoleSaveDTO> role);
    List<RoleExportVO> export(List<RoleEntity> role);
    List<RoleSaveDTO> imports(List<RoleExportVO> role);
}