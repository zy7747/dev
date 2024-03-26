package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.menu.MenuSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.vo.menu.MenuDetailVO;
import com.example.system.dal.vo.menu.MenuExportVO;
import com.example.system.dal.vo.menu.MenuListVO;
import com.example.system.dal.vo.menu.MenuPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-26T11:27:13+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class MenuConvertImpl implements MenuConvert {

    @Override
    public PageList<MenuPageVO> page(PageList<MenuEntity> menu) {
        if ( menu == null ) {
            return null;
        }

        PageList<MenuPageVO> pageList = new PageList<MenuPageVO>();

        pageList.setPage( menu.getPage() );
        pageList.setTotal( menu.getTotal() );
        pageList.setSize( menu.getSize() );
        pageList.setList( menuEntityListToMenuPageVOList( menu.getList() ) );

        return pageList;
    }

    @Override
    public List<MenuListVO> list(List<MenuEntity> menu) {
        if ( menu == null ) {
            return null;
        }

        List<MenuListVO> list = new ArrayList<MenuListVO>( menu.size() );
        for ( MenuEntity menuEntity : menu ) {
            list.add( menuEntityToMenuListVO( menuEntity ) );
        }

        return list;
    }

    @Override
    public MenuDetailVO detail(MenuEntity menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDetailVO menuDetailVO = new MenuDetailVO();

        menuDetailVO.setId( menu.getId() );
        menuDetailVO.setStatus( menu.getStatus() );
        menuDetailVO.setRemark( menu.getRemark() );
        menuDetailVO.setCreator( menu.getCreator() );
        menuDetailVO.setUpdater( menu.getUpdater() );
        menuDetailVO.setCreateTime( menu.getCreateTime() );
        menuDetailVO.setUpdateTime( menu.getUpdateTime() );
        menuDetailVO.setIsDeleted( menu.getIsDeleted() );
        menuDetailVO.setTenantId( menu.getTenantId() );
        menuDetailVO.setVersion( menu.getVersion() );
        menuDetailVO.setParentId( menu.getParentId() );
        menuDetailVO.setTitle( menu.getTitle() );
        menuDetailVO.setName( menu.getName() );
        menuDetailVO.setPath( menu.getPath() );
        menuDetailVO.setType( menu.getType() );
        menuDetailVO.setComponent( menu.getComponent() );
        menuDetailVO.setPermission( menu.getPermission() );
        menuDetailVO.setLevel( menu.getLevel() );
        menuDetailVO.setSort( menu.getSort() );
        menuDetailVO.setVisible( menu.getVisible() );
        menuDetailVO.setKeepAlive( menu.getKeepAlive() );
        menuDetailVO.setAlwaysShow( menu.getAlwaysShow() );
        menuDetailVO.setIcon( menu.getIcon() );
        menuDetailVO.setDescription( menu.getDescription() );
        menuDetailVO.setMeta( menu.getMeta() );

        return menuDetailVO;
    }

    @Override
    public MenuEntity save(MenuSaveDTO menu) {
        if ( menu == null ) {
            return null;
        }

        MenuEntity menuEntity = new MenuEntity();

        menuEntity.setId( menu.getId() );
        menuEntity.setStatus( menu.getStatus() );
        menuEntity.setCreator( menu.getCreator() );
        menuEntity.setUpdater( menu.getUpdater() );
        menuEntity.setRemark( menu.getRemark() );
        menuEntity.setCreateTime( menu.getCreateTime() );
        menuEntity.setUpdateTime( menu.getUpdateTime() );
        menuEntity.setIsDeleted( menu.getIsDeleted() );
        menuEntity.setTenantId( menu.getTenantId() );
        menuEntity.setVersion( menu.getVersion() );
        menuEntity.setParentId( menu.getParentId() );
        menuEntity.setTitle( menu.getTitle() );
        menuEntity.setName( menu.getName() );
        menuEntity.setPath( menu.getPath() );
        menuEntity.setType( menu.getType() );
        menuEntity.setComponent( menu.getComponent() );
        menuEntity.setPermission( menu.getPermission() );
        menuEntity.setLevel( menu.getLevel() );
        menuEntity.setSort( menu.getSort() );
        menuEntity.setVisible( menu.getVisible() );
        menuEntity.setKeepAlive( menu.getKeepAlive() );
        menuEntity.setAlwaysShow( menu.getAlwaysShow() );
        menuEntity.setIcon( menu.getIcon() );
        menuEntity.setDescription( menu.getDescription() );
        menuEntity.setMeta( menu.getMeta() );

        return menuEntity;
    }

    @Override
    public List<MenuEntity> saveList(List<MenuSaveDTO> menu) {
        if ( menu == null ) {
            return null;
        }

        List<MenuEntity> list = new ArrayList<MenuEntity>( menu.size() );
        for ( MenuSaveDTO menuSaveDTO : menu ) {
            list.add( save( menuSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<MenuExportVO> export(List<MenuEntity> menu) {
        if ( menu == null ) {
            return null;
        }

        List<MenuExportVO> list = new ArrayList<MenuExportVO>( menu.size() );
        for ( MenuEntity menuEntity : menu ) {
            list.add( menuEntityToMenuExportVO( menuEntity ) );
        }

        return list;
    }

    @Override
    public List<MenuSaveDTO> imports(List<MenuExportVO> menu) {
        if ( menu == null ) {
            return null;
        }

        List<MenuSaveDTO> list = new ArrayList<MenuSaveDTO>( menu.size() );
        for ( MenuExportVO menuExportVO : menu ) {
            list.add( menuExportVOToMenuSaveDTO( menuExportVO ) );
        }

        return list;
    }

    protected MenuPageVO menuEntityToMenuPageVO(MenuEntity menuEntity) {
        if ( menuEntity == null ) {
            return null;
        }

        MenuPageVO menuPageVO = new MenuPageVO();

        menuPageVO.setId( menuEntity.getId() );
        menuPageVO.setStatus( menuEntity.getStatus() );
        menuPageVO.setRemark( menuEntity.getRemark() );
        menuPageVO.setCreator( menuEntity.getCreator() );
        menuPageVO.setUpdater( menuEntity.getUpdater() );
        menuPageVO.setCreateTime( menuEntity.getCreateTime() );
        menuPageVO.setUpdateTime( menuEntity.getUpdateTime() );
        menuPageVO.setIsDeleted( menuEntity.getIsDeleted() );
        menuPageVO.setTenantId( menuEntity.getTenantId() );
        menuPageVO.setVersion( menuEntity.getVersion() );
        menuPageVO.setParentId( menuEntity.getParentId() );
        menuPageVO.setTitle( menuEntity.getTitle() );
        menuPageVO.setName( menuEntity.getName() );
        menuPageVO.setPath( menuEntity.getPath() );
        menuPageVO.setType( menuEntity.getType() );
        menuPageVO.setComponent( menuEntity.getComponent() );
        menuPageVO.setPermission( menuEntity.getPermission() );
        menuPageVO.setLevel( menuEntity.getLevel() );
        menuPageVO.setSort( menuEntity.getSort() );
        menuPageVO.setVisible( menuEntity.getVisible() );
        menuPageVO.setKeepAlive( menuEntity.getKeepAlive() );
        menuPageVO.setAlwaysShow( menuEntity.getAlwaysShow() );
        menuPageVO.setIcon( menuEntity.getIcon() );
        menuPageVO.setDescription( menuEntity.getDescription() );
        menuPageVO.setMeta( menuEntity.getMeta() );

        return menuPageVO;
    }

    protected List<MenuPageVO> menuEntityListToMenuPageVOList(List<MenuEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<MenuPageVO> list1 = new ArrayList<MenuPageVO>( list.size() );
        for ( MenuEntity menuEntity : list ) {
            list1.add( menuEntityToMenuPageVO( menuEntity ) );
        }

        return list1;
    }

    protected MenuListVO menuEntityToMenuListVO(MenuEntity menuEntity) {
        if ( menuEntity == null ) {
            return null;
        }

        MenuListVO menuListVO = new MenuListVO();

        menuListVO.setId( menuEntity.getId() );
        menuListVO.setStatus( menuEntity.getStatus() );
        menuListVO.setRemark( menuEntity.getRemark() );
        menuListVO.setCreator( menuEntity.getCreator() );
        menuListVO.setUpdater( menuEntity.getUpdater() );
        menuListVO.setCreateTime( menuEntity.getCreateTime() );
        menuListVO.setUpdateTime( menuEntity.getUpdateTime() );
        menuListVO.setIsDeleted( menuEntity.getIsDeleted() );
        menuListVO.setTenantId( menuEntity.getTenantId() );
        menuListVO.setVersion( menuEntity.getVersion() );
        menuListVO.setParentId( menuEntity.getParentId() );
        menuListVO.setTitle( menuEntity.getTitle() );
        menuListVO.setName( menuEntity.getName() );
        menuListVO.setPath( menuEntity.getPath() );
        menuListVO.setType( menuEntity.getType() );
        menuListVO.setComponent( menuEntity.getComponent() );
        menuListVO.setPermission( menuEntity.getPermission() );
        menuListVO.setLevel( menuEntity.getLevel() );
        menuListVO.setSort( menuEntity.getSort() );
        menuListVO.setVisible( menuEntity.getVisible() );
        menuListVO.setKeepAlive( menuEntity.getKeepAlive() );
        menuListVO.setAlwaysShow( menuEntity.getAlwaysShow() );
        menuListVO.setIcon( menuEntity.getIcon() );
        menuListVO.setDescription( menuEntity.getDescription() );
        menuListVO.setMeta( menuEntity.getMeta() );

        return menuListVO;
    }

    protected MenuExportVO menuEntityToMenuExportVO(MenuEntity menuEntity) {
        if ( menuEntity == null ) {
            return null;
        }

        MenuExportVO menuExportVO = new MenuExportVO();

        menuExportVO.setStatus( menuEntity.getStatus() );
        menuExportVO.setRemark( menuEntity.getRemark() );
        menuExportVO.setCreator( menuEntity.getCreator() );
        menuExportVO.setUpdater( menuEntity.getUpdater() );
        menuExportVO.setCreateTime( menuEntity.getCreateTime() );
        menuExportVO.setUpdateTime( menuEntity.getUpdateTime() );
        menuExportVO.setParentId( menuEntity.getParentId() );
        menuExportVO.setTitle( menuEntity.getTitle() );
        menuExportVO.setName( menuEntity.getName() );
        menuExportVO.setPath( menuEntity.getPath() );
        menuExportVO.setType( menuEntity.getType() );
        menuExportVO.setComponent( menuEntity.getComponent() );
        menuExportVO.setPermission( menuEntity.getPermission() );
        menuExportVO.setLevel( menuEntity.getLevel() );
        menuExportVO.setSort( menuEntity.getSort() );
        menuExportVO.setVisible( menuEntity.getVisible() );
        menuExportVO.setKeepAlive( menuEntity.getKeepAlive() );
        menuExportVO.setAlwaysShow( menuEntity.getAlwaysShow() );
        menuExportVO.setIcon( menuEntity.getIcon() );
        menuExportVO.setDescription( menuEntity.getDescription() );
        menuExportVO.setMeta( menuEntity.getMeta() );

        return menuExportVO;
    }

    protected MenuSaveDTO menuExportVOToMenuSaveDTO(MenuExportVO menuExportVO) {
        if ( menuExportVO == null ) {
            return null;
        }

        MenuSaveDTO menuSaveDTO = new MenuSaveDTO();

        menuSaveDTO.setStatus( menuExportVO.getStatus() );
        menuSaveDTO.setCreator( menuExportVO.getCreator() );
        menuSaveDTO.setUpdater( menuExportVO.getUpdater() );
        menuSaveDTO.setRemark( menuExportVO.getRemark() );
        menuSaveDTO.setCreateTime( menuExportVO.getCreateTime() );
        menuSaveDTO.setUpdateTime( menuExportVO.getUpdateTime() );
        menuSaveDTO.setParentId( menuExportVO.getParentId() );
        menuSaveDTO.setTitle( menuExportVO.getTitle() );
        menuSaveDTO.setName( menuExportVO.getName() );
        menuSaveDTO.setPath( menuExportVO.getPath() );
        menuSaveDTO.setType( menuExportVO.getType() );
        menuSaveDTO.setComponent( menuExportVO.getComponent() );
        menuSaveDTO.setPermission( menuExportVO.getPermission() );
        menuSaveDTO.setLevel( menuExportVO.getLevel() );
        menuSaveDTO.setSort( menuExportVO.getSort() );
        menuSaveDTO.setVisible( menuExportVO.getVisible() );
        menuSaveDTO.setKeepAlive( menuExportVO.getKeepAlive() );
        menuSaveDTO.setAlwaysShow( menuExportVO.getAlwaysShow() );
        menuSaveDTO.setIcon( menuExportVO.getIcon() );
        menuSaveDTO.setDescription( menuExportVO.getDescription() );
        menuSaveDTO.setMeta( menuExportVO.getMeta() );

        return menuSaveDTO;
    }
}
