package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.person.PersonQueryDTO;
import com.example.system.dal.entity.PersonEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface PersonMapper extends BaseMapper<PersonEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<PersonEntity> search(PersonQueryDTO person) {
        QueryWrapper<PersonEntity> wrapper = new QueryWrapper<>();

        /* 用户id */
        if (person.getUserId() != null) {
            wrapper.eq("user_id", person.getUserId());
        }
        /* 照片 */
        if (!StrUtil.hasBlank(person.getPhoto())) {
            wrapper.eq("photo", person.getPhoto());
        }
        /* 姓名 */
        if (!StrUtil.hasBlank(person.getName())) {
            wrapper.eq("name", person.getName());
        }
        /* 性别 */
        if (!StrUtil.hasBlank(person.getSex())) {
            wrapper.eq("sex", person.getSex());
        }
        /* 职业 */
        if (!StrUtil.hasBlank(person.getProfession())) {
            wrapper.eq("profession", person.getProfession());
        }
        /* 国家 */
        if (!StrUtil.hasBlank(person.getNation())) {
            wrapper.eq("nation", person.getNation());
        }
        /* 籍贯 */
        if (!StrUtil.hasBlank(person.getOrigin())) {
            wrapper.eq("origin", person.getOrigin());
        }
        /* 住址 */
        if (!StrUtil.hasBlank(person.getAddress())) {
            wrapper.eq("address", person.getAddress());
        }
        /* 最高学历 */
        if (!StrUtil.hasBlank(person.getDegree())) {
            wrapper.eq("degree", person.getDegree());
        }
        /* 身高 */
        if (!StrUtil.hasBlank(person.getHeight())) {
            wrapper.eq("height", person.getHeight());
        }
        /* 体重 */
        if (!StrUtil.hasBlank(person.getWeight())) {
            wrapper.eq("weight", person.getWeight());
        }
        /* 爱好 */
        if (!StrUtil.hasBlank(person.getHobby())) {
            wrapper.eq("hobby", person.getHobby());
        }
        /* 年龄 */
        if (person.getAge() != null) {
            wrapper.eq("age", person.getAge());
        }
        /* 身份证号码 */
        if (!StrUtil.hasBlank(person.getIdCard())) {
            wrapper.eq("id_card", person.getIdCard());
        }
        /* 电话号码 */
        if (!StrUtil.hasBlank(person.getPhone())) {
            wrapper.eq("phone", person.getPhone());
        }
        /* 电子邮箱 */
        if (!StrUtil.hasBlank(person.getEmail())) {
            wrapper.eq("email", person.getEmail());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(person.getStatus())) {
            wrapper.eq("status", person.getStatus());
        }
        /* 创建时间 */
        if (person.getCreateTime() != null) {
            wrapper.like("create_time", person.getCreateTime());
        }
        /* 更新时间 */
        if (person.getUpdateTime() != null) {
            wrapper.eq("update_time", person.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<PersonEntity>> onlyValidList(List<PersonEntity> personData) {
        List<PersonEntity> personList = queryList(new PersonQueryDTO());
        for (PersonEntity item : personData) {
            Result<PersonEntity> person = onlyValid(item, personList);
            if (person.getCode() != 200) {
                return Result.fail(person.getMessage());
            }
        }
        return Result.success(personList);
    }

    /**
     * 唯一性校验
     */
    default Result<PersonEntity> onlyValid(PersonEntity person, List<PersonEntity> personList) {
        for (PersonEntity item : personList) {
            //修改跳过自己
            if (person.getId() != null && item.getId().equals(person.getId())) {
                continue;
            }
            if (!StrUtil.hasBlank(person.getIdCard()) && Objects.equals(person.getIdCard(), item.getIdCard())) {
                return Result.fail("身份证号码已被使用");
            }
        }
        return Result.success(person);
    }

    /**
     * 分页查询
     */
    default PageList<PersonEntity> queryPage(PersonQueryDTO person) {
        IPage<PersonEntity> pageParams = new Page<>(person.getPage(), person.getSize());
        return PageList.setPages(selectPage(pageParams, search(person)));
    }

    /**
     * 列表查询
     */
    default List<PersonEntity> queryList(PersonQueryDTO person) {
        return selectList(search(person));
    }
}