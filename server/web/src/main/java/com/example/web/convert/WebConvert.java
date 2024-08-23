package com.example.web.convert;

import com.example.framework.common.PageList;
import com.example.web.dal.dto.web.WebSaveDTO;
import com.example.web.dal.entity.WebEntity;
import com.example.web.dal.vo.web.WebDetailVO;
import com.example.web.dal.vo.web.WebExportVO;
import com.example.web.dal.vo.web.WebListVO;
import com.example.web.dal.vo.web.WebPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WebConvert {
    WebConvert INSTANCE = Mappers.getMapper(WebConvert.class);

    PageList<WebPageVO> page(PageList<WebEntity> web);

    List<WebListVO> list(List<WebEntity> web);

    WebDetailVO detail(WebEntity web);

    WebEntity save(WebSaveDTO web);

    List<WebEntity> saveList(List<WebSaveDTO> web);

    List<WebExportVO> export(List<WebEntity> web);

    List<WebSaveDTO> imports(List<WebExportVO> web);
}