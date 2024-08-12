package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.person.PersonQueryDTO;
import com.example.system.dal.dto.person.PersonSaveDTO;
import com.example.system.dal.entity.PersonEntity;
import com.example.system.dal.vo.person.PersonDetailVO;
import com.example.system.dal.vo.person.PersonListVO;
import com.example.system.dal.vo.person.PersonPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface PersonService {
    /**
     * 获取列表分页
     *
     * @param person 分页入参
     * @return 列表分页
     */
    Result<PageList<PersonPageVO>> personPage(PersonQueryDTO person);

    /**
     * 获取列表
     *
     * @param person 入参
     * @return 列表
     */
    Result<List<PersonListVO>> personList(PersonQueryDTO person);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<PersonDetailVO> personDetail(Long id);

    /**
     * 新增/修改
     *
     * @param person 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<PersonEntity> personSave(PersonSaveDTO person);

    /**
     * 批量新增/修改
     *
     * @param person 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<PersonEntity>> personSaveList(@Valid List<PersonSaveDTO> person);

    /**
     * 导出
     *
     * @param response,person 入参
     */
    void personExport(HttpServletResponse response, PersonQueryDTO person) throws IOException;
}