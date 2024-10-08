package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.PersonConvert;
import com.example.system.dal.dto.person.PersonQueryDTO;
import com.example.system.dal.dto.person.PersonSaveDTO;
import com.example.system.dal.entity.PersonEntity;
import com.example.system.dal.vo.person.PersonExportVO;
import com.example.system.mapper.PersonMapper;
import com.example.system.dal.vo.person.PersonDetailVO;
import com.example.system.dal.vo.person.PersonListVO;
import com.example.system.dal.vo.person.PersonPageVO;
import com.example.system.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonEntity> implements PersonService {
    @Resource
    PersonMapper personMapper;

    /**
     * 获取列表分页
     *
     * @param person 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<PersonPageVO>> personPage(PersonQueryDTO person) {
        return Result.success(PersonConvert.INSTANCE.page(personMapper.queryPage(person)));
    }

    /**
     * 获取列表
     *
     * @param person 入参
     * @return 列表
     */
    @Override
    public Result<List<PersonListVO>> personList(PersonQueryDTO person) {
        return Result.success(PersonConvert.INSTANCE.list(personMapper.queryList(person)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<PersonDetailVO> personDetail(Long id) {
        return Result.success(PersonConvert.INSTANCE.detail(personMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param person 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<PersonEntity> personSave(PersonSaveDTO person) {
        PersonEntity personData = PersonConvert.INSTANCE.save(person);
        List<PersonEntity> personList = personMapper.queryList(new PersonQueryDTO());
        Result<PersonEntity> valid = personMapper.onlyValid(personData, personList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(personData);
            return Result.success(personData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param person 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<PersonEntity>> personSaveList(List<PersonSaveDTO> person) {
        List<PersonEntity> personData = PersonConvert.INSTANCE.saveList(person);
        Result<List<PersonEntity>> valid = personMapper.onlyValidList(personData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(personData);
            return Result.success(personData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,person 入参
     */
    @Override
    public void personExport(HttpServletResponse response, PersonQueryDTO person) throws IOException {
        ExcelUtils.export(response, "个人信息.xlsx", "个人信息", PersonExportVO.class, PersonConvert.INSTANCE.export(personMapper.queryList(person)));
    }
}