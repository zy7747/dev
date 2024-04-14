package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.permission.PermissionQueryDTO;
import com.example.system.dal.dto.permission.PermissionSaveDTO;
import com.example.system.dal.entity.PermissionEntity;
import com.example.system.dal.vo.permission.PermissionDetailVO;
import com.example.system.dal.vo.permission.PermissionListVO;
import com.example.system.dal.vo.permission.PermissionPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface PermissionService {
    /**
     * 获取列表分页
     *
     * @param permission 分页入参
     * @return 列表分页
     */
    Result<PageList<PermissionPageVO>> permissionPage(PermissionQueryDTO permission);

    /**
     * 获取列表
     *
     * @param permission 入参
     * @return 列表
     */
    Result<List<PermissionListVO>> permissionList(PermissionQueryDTO permission);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<PermissionDetailVO> permissionDetail(Long id);

    /**
     * 新增/修改
     *
     * @param permission 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<PermissionEntity> permissionSave(PermissionSaveDTO permission);

    /**
     * 批量新增/修改
     *
     * @param permission 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<PermissionEntity>> permissionSaveList(@Valid List<PermissionSaveDTO> permission);

    /**
     * 导出
     *
     * @param response,permission 入参
     */
    void permissionExport(HttpServletResponse response, PermissionQueryDTO permission) throws IOException;
}