package com.example.system.convert;
import com.example.framework.common.PageList;
import com.example.system.dal.dto.menu.MenuSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.vo.menu.MenuDetailVO;
import com.example.system.dal.vo.menu.MenuExportVO;
import com.example.system.dal.vo.menu.MenuListVO;
import com.example.system.dal.vo.menu.MenuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface MenuConvert {
    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);
    PageList<MenuPageVO> page(PageList<MenuEntity> menu);
    List<MenuListVO> list(List<MenuEntity> menu);
    MenuDetailVO detail(MenuEntity menu);
    MenuEntity save(MenuSaveDTO menu);
    List<MenuEntity> saveList(List<MenuSaveDTO> menu);
    List<MenuExportVO> export(List<MenuEntity> menu);
    List<MenuSaveDTO> imports(List<MenuExportVO> menu);
}