package com.example.system.convert;
import com.example.framework.common.PageList;
import com.example.system.dal.dto.page.PageSaveDTO;
import com.example.system.dal.entity.PageEntity;
import com.example.system.dal.vo.page.PageDetailVO;
import com.example.system.dal.vo.page.PageExportVO;
import com.example.system.dal.vo.page.PageListVO;
import com.example.system.dal.vo.page.PagePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface PageConvert {
    PageConvert INSTANCE = Mappers.getMapper(PageConvert.class);
    PageList<PagePageVO> page(PageList<PageEntity> page);
    List<PageListVO> list(List<PageEntity> page);
    PageDetailVO detail(PageEntity page);
    PageEntity save(PageSaveDTO page);
    List<PageEntity> saveList(List<PageSaveDTO> page);
    List<PageExportVO> export(List<PageEntity> page);
    List<PageSaveDTO> imports(List<PageExportVO> page);
}