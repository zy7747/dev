package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.person.PersonSaveDTO;
import com.example.system.dal.entity.PersonEntity;
import com.example.system.dal.vo.person.PersonDetailVO;
import com.example.system.dal.vo.person.PersonExportVO;
import com.example.system.dal.vo.person.PersonListVO;
import com.example.system.dal.vo.person.PersonPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonConvert {
    PersonConvert INSTANCE = Mappers.getMapper(PersonConvert.class);

    PageList<PersonPageVO> page(PageList<PersonEntity> person);

    List<PersonListVO> list(List<PersonEntity> person);

    PersonDetailVO detail(PersonEntity person);

    PersonEntity save(PersonSaveDTO person);

    List<PersonEntity> saveList(List<PersonSaveDTO> person);

    List<PersonExportVO> export(List<PersonEntity> person);

    List<PersonSaveDTO> imports(List<PersonExportVO> person);
}