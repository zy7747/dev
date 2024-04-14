package com.example.system.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.MenuConvert;
import com.example.system.dal.dto.menu.MenuQueryDTO;
import com.example.system.dal.dto.menu.MenuSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.vo.menu.MenuExportVO;
import com.example.system.mapper.MenuMapper;
import com.example.system.dal.vo.menu.MenuDetailVO;
import com.example.system.dal.vo.menu.MenuListVO;
import com.example.system.dal.vo.menu.MenuPageVO;
import com.example.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Resource
    MenuMapper menuMapper;
    /**
     * 获取列表分页
     *
     * @param menu 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<MenuPageVO>> menuPage(MenuQueryDTO menu) {
        return Result.success(MenuConvert.INSTANCE.page(menuMapper.queryPage(menu)));
    }
    /**
     * 获取列表
     *
     * @param menu 入参
     * @return 列表
     */
    @Override
    public Result<List<MenuListVO>> menuList(MenuQueryDTO menu) {
        return Result.success(MenuConvert.INSTANCE.list(menuMapper.queryList(menu)));
    }
    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<MenuDetailVO> menuDetail(Long id) {
        return Result.success(MenuConvert.INSTANCE.detail(menuMapper.selectById(id)));
    }
    /**
     * 新增/修改
     *
     * @param menu 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<MenuEntity> menuSave(MenuSaveDTO menu) {
        MenuEntity menuData = MenuConvert.INSTANCE.save(menu);
        List<MenuEntity> menuList = menuMapper.queryList(new MenuQueryDTO());
        Result<MenuEntity> valid = menuMapper.onlyValid(menuData, menuList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(menuData);
            return Result.success(menuData);
        } else {
            return valid;
        }
    }
    /**
     * 批量新增/修改
     *
     * @param menu 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<MenuEntity>> menuSaveList(List<MenuSaveDTO> menu) {
        List<MenuEntity> menuData = MenuConvert.INSTANCE.saveList(menu);
        Result<List<MenuEntity>> valid = menuMapper.onlyValidList(menuData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(menuData);
            return Result.success(menuData);
        } else {
            return valid;
        }
    }
    /**
     * 导出
     *
     * @param response,menu 入参
     */
    @Override
    public void menuExport(HttpServletResponse response, MenuQueryDTO menu) throws IOException {
        ExcelUtils.export(response, "菜单.xlsx", "菜单", MenuExportVO.class, MenuConvert.INSTANCE.export(menuMapper.queryList(menu)));
    }
}