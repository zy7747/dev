package com.example.system.service;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.menu.MenuQueryDTO;
import com.example.system.dal.dto.menu.MenuSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.vo.menu.MenuDetailVO;
import com.example.system.dal.vo.menu.MenuListVO;
import com.example.system.dal.vo.menu.MenuPageVO;
import org.springframework.validation.annotation.Validated;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Validated
public interface MenuService {
    /**
     * 获取列表分页
     *
     * @param menu 分页入参
     * @return 列表分页
     */
    Result<PageList<MenuPageVO>> menuPage(MenuQueryDTO menu);
    /**
     * 获取列表
     *
     * @param menu 入参
     * @return 列表
     */
    Result<List<MenuListVO>> menuList(MenuQueryDTO menu);
    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<MenuDetailVO> menuDetail(Long id);
    /**
     * 新增/修改
     *
     * @param menu 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<MenuEntity> menuSave(MenuSaveDTO menu);
    /**
     * 批量新增/修改
     *
     * @param menu 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<MenuEntity>> menuSaveList(@Valid List<MenuSaveDTO> menu);
    /**
     * 导出
     *
     * @param response,menu 入参
     */
    void menuExport(HttpServletResponse response, MenuQueryDTO menu) throws IOException;
}