package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.RoleConvert;
import com.example.system.dal.dto.role.RoleQueryDTO;
import com.example.system.dal.dto.role.RoleSaveDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.vo.role.RoleExportVO;
import com.example.system.mapper.RoleMapper;
import com.example.system.dal.vo.role.RoleDetailVO;
import com.example.system.dal.vo.role.RoleListVO;
import com.example.system.dal.vo.role.RolePageVO;
import com.example.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Resource
    RoleMapper roleMapper;

    /**
     * 获取列表分页
     *
     * @param role 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<RolePageVO>> rolePage(RoleQueryDTO role) {
        return Result.success(RoleConvert.INSTANCE.page(roleMapper.queryPage(role)));
    }

    /**
     * 获取列表
     *
     * @param role 入参
     * @return 列表
     */
    @Override
    public Result<List<RoleListVO>> roleList(RoleQueryDTO role) {
        return Result.success(RoleConvert.INSTANCE.list(roleMapper.queryList(role)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<RoleDetailVO> roleDetail(Long id) {

        ArrayList<Long> menus = new ArrayList<>();

        roleMapper.selectRoleMenu(id).forEach(item -> menus.add(item.getMenuId()));

        RoleDetailVO roles = RoleConvert.INSTANCE.detail(roleMapper.selectById(id));

        roles.setMenus(menus);

        return Result.success(roles);

    }

    /**
     * 新增/修改
     *
     * @param role 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<RoleEntity> roleSave(RoleSaveDTO role) {
        RoleEntity roleData = RoleConvert.INSTANCE.save(role);
        List<RoleEntity> roleList = roleMapper.queryList(new RoleQueryDTO());
        Result<RoleEntity> valid = roleMapper.onlyValid(roleData, roleList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(roleData);

            if (role.getMenus() != null && role.getMenus().size() > 0) {
                //先删除所有user下的角色
                roleMapper.deleteRoleMenu(roleData.getId());
                //循环角色列表
                for (Long menu : role.getMenus()) {
                    //将角色数据塞进去
                    roleMapper.insertRoleMenu(roleData.getId(), menu);
                }
            }

            return Result.success(roleData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param role 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<RoleEntity>> roleSaveList(List<RoleSaveDTO> role) {
        List<RoleEntity> roleData = RoleConvert.INSTANCE.saveList(role);
        Result<List<RoleEntity>> valid = roleMapper.onlyValidList(roleData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(roleData);
            return Result.success(roleData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,role 入参
     */
    @Override
    public void roleExport(HttpServletResponse response, RoleQueryDTO role) throws IOException {
        ExcelUtils.export(response, "角色.xlsx", "角色", RoleExportVO.class, RoleConvert.INSTANCE.export(roleMapper.queryList(role)));
    }
}