package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.PermissionConvert;
import com.example.system.dal.dto.permission.PermissionQueryDTO;
import com.example.system.dal.dto.permission.PermissionSaveDTO;
import com.example.system.dal.entity.PermissionEntity;
import com.example.system.dal.vo.permission.PermissionExportVO;
import com.example.system.mapper.PermissionMapper;
import com.example.system.dal.vo.permission.PermissionDetailVO;
import com.example.system.dal.vo.permission.PermissionListVO;
import com.example.system.dal.vo.permission.PermissionPageVO;
import com.example.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
    @Resource
    PermissionMapper permissionMapper;

    /**
     * 获取列表分页
     *
     * @param permission 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<PermissionPageVO>> permissionPage(PermissionQueryDTO permission) {
        return Result.success(PermissionConvert.INSTANCE.page(permissionMapper.queryPage(permission)));
    }

    /**
     * 获取列表
     *
     * @param permission 入参
     * @return 列表
     */
    @Override
    public Result<List<PermissionListVO>> permissionList(PermissionQueryDTO permission) {
        return Result.success(PermissionConvert.INSTANCE.list(permissionMapper.queryList(permission)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<PermissionDetailVO> permissionDetail(Long id) {
        return Result.success(PermissionConvert.INSTANCE.detail(permissionMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param permission 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<PermissionEntity> permissionSave(PermissionSaveDTO permission) {
        PermissionEntity permissionData = PermissionConvert.INSTANCE.save(permission);
        List<PermissionEntity> permissionList = permissionMapper.queryList(new PermissionQueryDTO());
        Result<PermissionEntity> valid = permissionMapper.onlyValid(permissionData, permissionList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(permissionData);
            return Result.success(permissionData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param permission 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<PermissionEntity>> permissionSaveList(List<PermissionSaveDTO> permission) {
        List<PermissionEntity> permissionData = PermissionConvert.INSTANCE.saveList(permission);
        Result<List<PermissionEntity>> valid = permissionMapper.onlyValidList(permissionData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(permissionData);
            return Result.success(permissionData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,permission 入参
     */
    @Override
    public void permissionExport(HttpServletResponse response, PermissionQueryDTO permission) throws IOException {
        ExcelUtils.export(response, "权限.xlsx", "权限", PermissionExportVO.class, PermissionConvert.INSTANCE.export(permissionMapper.queryList(permission)));
    }
}