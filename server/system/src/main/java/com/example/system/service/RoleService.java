package com.example.system.service;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.role.RoleQueryDTO;
import com.example.system.dal.dto.role.RoleSaveDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.vo.role.RoleDetailVO;
import com.example.system.dal.vo.role.RoleListVO;
import com.example.system.dal.vo.role.RolePageVO;
import org.springframework.validation.annotation.Validated;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Validated
public interface RoleService {
    /**
     * 获取列表分页
     *
     * @param role 分页入参
     * @return 列表分页
     */
    Result<PageList<RolePageVO>> rolePage(RoleQueryDTO role);
    /**
     * 获取列表
     *
     * @param role 入参
     * @return 列表
     */
    Result<List<RoleListVO>> roleList(RoleQueryDTO role);
    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<RoleDetailVO> roleDetail(Long id);
    /**
     * 新增/修改
     *
     * @param role 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<RoleEntity> roleSave(RoleSaveDTO role);
    /**
     * 批量新增/修改
     *
     * @param role 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<RoleEntity>> roleSaveList(@Valid List<RoleSaveDTO> role);
    /**
     * 导出
     *
     * @param response,role 入参
     */
    void roleExport(HttpServletResponse response, RoleQueryDTO role) throws IOException;
}