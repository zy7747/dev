package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.DictConvert;
import com.example.system.dal.dto.dict.DictQueryDTO;
import com.example.system.dal.dto.dict.DictSaveDTO;
import com.example.system.dal.entity.DictEntity;
import com.example.system.dal.vo.dict.DictExportVO;
import com.example.system.mapper.DictMapper;
import com.example.system.dal.vo.dict.DictDetailVO;
import com.example.system.dal.vo.dict.DictListVO;
import com.example.system.dal.vo.dict.DictPageVO;
import com.example.system.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {
    @Resource
    DictMapper dictMapper;

    /**
     * 获取列表分页
     *
     * @param dict 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<DictPageVO>> dictPage(DictQueryDTO dict) {
        return Result.success(DictConvert.INSTANCE.page(dictMapper.queryPage(dict)));
    }

    /**
     * 获取列表
     *
     * @param dict 入参
     * @return 列表
     */
    @Override
    public Result<List<DictListVO>> dictList(DictQueryDTO dict) {
        return Result.success(DictConvert.INSTANCE.list(dictMapper.queryList(dict)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<DictDetailVO> dictDetail(Long id) {
        DictQueryDTO dict = new DictQueryDTO();

        dict.setParentId(id);

        List<DictEntity> dictList = dictMapper.getSubList(dict);

        DictDetailVO dictDetail = DictConvert.INSTANCE.detail(dictMapper.selectById(id));

        dictDetail.setDictList(dictList);

        return Result.success(dictDetail);
    }

    /**
     * 新增/修改
     *
     * @param dict 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<DictEntity> dictSave(DictSaveDTO dict) {
        DictEntity dictData = DictConvert.INSTANCE.save(dict);
        List<DictEntity> dictList = dictMapper.queryList(new DictQueryDTO());
        Result<DictEntity> valid = dictMapper.onlyValid(dictData, dictList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(dictData);
            return Result.success(dictData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param dict 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<DictEntity>> dictSaveList(List<DictSaveDTO> dict) {
        List<DictEntity> dictData = DictConvert.INSTANCE.saveList(dict);
        Result<List<DictEntity>> valid = dictMapper.onlyValidList(dictData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(dictData);
            return Result.success(dictData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,dict 入参
     */
    @Override
    public void dictExport(HttpServletResponse response, DictQueryDTO dict) throws IOException {
        ExcelUtils.export(response, "字典.xlsx", "字典", DictExportVO.class, DictConvert.INSTANCE.export(dictMapper.queryList(dict)));
    }

    /**
     * 获取子列表
     *
     * @param dict 入参
     * @return 子列表
     */
    @Override
    public Result<List<DictEntity>> subList(DictQueryDTO dict) {
        List<DictEntity> dictList = dictMapper.getSubList(dict);

        return Result.success(dictList);
    }

    /**
     * 获取子列表
     *
     * @return 字典Map
     */
    @Override
    public Result<Object> dictMap() {
        return Result.success(dictMapper.getDictMap());
    }
}