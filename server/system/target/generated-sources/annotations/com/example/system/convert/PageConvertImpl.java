package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.page.PageSaveDTO;
import com.example.system.dal.entity.PageEntity;
import com.example.system.dal.vo.page.PageDetailVO;
import com.example.system.dal.vo.page.PageExportVO;
import com.example.system.dal.vo.page.PageListVO;
import com.example.system.dal.vo.page.PagePageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-26T11:27:13+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class PageConvertImpl implements PageConvert {

    @Override
    public PageList<PagePageVO> page(PageList<PageEntity> page) {
        if ( page == null ) {
            return null;
        }

        PageList<PagePageVO> pageList = new PageList<PagePageVO>();

        pageList.setPage( page.getPage() );
        pageList.setTotal( page.getTotal() );
        pageList.setSize( page.getSize() );
        pageList.setList( pageEntityListToPagePageVOList( page.getList() ) );

        return pageList;
    }

    @Override
    public List<PageListVO> list(List<PageEntity> page) {
        if ( page == null ) {
            return null;
        }

        List<PageListVO> list = new ArrayList<PageListVO>( page.size() );
        for ( PageEntity pageEntity : page ) {
            list.add( pageEntityToPageListVO( pageEntity ) );
        }

        return list;
    }

    @Override
    public PageDetailVO detail(PageEntity page) {
        if ( page == null ) {
            return null;
        }

        PageDetailVO pageDetailVO = new PageDetailVO();

        pageDetailVO.setId( page.getId() );
        pageDetailVO.setStatus( page.getStatus() );
        pageDetailVO.setRemark( page.getRemark() );
        pageDetailVO.setCreator( page.getCreator() );
        pageDetailVO.setUpdater( page.getUpdater() );
        pageDetailVO.setCreateTime( page.getCreateTime() );
        pageDetailVO.setUpdateTime( page.getUpdateTime() );
        pageDetailVO.setIsDeleted( page.getIsDeleted() );
        pageDetailVO.setTenantId( page.getTenantId() );
        pageDetailVO.setVersion( page.getVersion() );
        pageDetailVO.setMenuId( page.getMenuId() );
        pageDetailVO.setOptions( page.getOptions() );

        return pageDetailVO;
    }

    @Override
    public PageEntity save(PageSaveDTO page) {
        if ( page == null ) {
            return null;
        }

        PageEntity pageEntity = new PageEntity();

        pageEntity.setId( page.getId() );
        pageEntity.setStatus( page.getStatus() );
        pageEntity.setCreator( page.getCreator() );
        pageEntity.setUpdater( page.getUpdater() );
        pageEntity.setRemark( page.getRemark() );
        pageEntity.setCreateTime( page.getCreateTime() );
        pageEntity.setUpdateTime( page.getUpdateTime() );
        pageEntity.setIsDeleted( page.getIsDeleted() );
        pageEntity.setTenantId( page.getTenantId() );
        pageEntity.setVersion( page.getVersion() );
        pageEntity.setMenuId( page.getMenuId() );
        pageEntity.setOptions( page.getOptions() );

        return pageEntity;
    }

    @Override
    public List<PageEntity> saveList(List<PageSaveDTO> page) {
        if ( page == null ) {
            return null;
        }

        List<PageEntity> list = new ArrayList<PageEntity>( page.size() );
        for ( PageSaveDTO pageSaveDTO : page ) {
            list.add( save( pageSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<PageExportVO> export(List<PageEntity> page) {
        if ( page == null ) {
            return null;
        }

        List<PageExportVO> list = new ArrayList<PageExportVO>( page.size() );
        for ( PageEntity pageEntity : page ) {
            list.add( pageEntityToPageExportVO( pageEntity ) );
        }

        return list;
    }

    @Override
    public List<PageSaveDTO> imports(List<PageExportVO> page) {
        if ( page == null ) {
            return null;
        }

        List<PageSaveDTO> list = new ArrayList<PageSaveDTO>( page.size() );
        for ( PageExportVO pageExportVO : page ) {
            list.add( pageExportVOToPageSaveDTO( pageExportVO ) );
        }

        return list;
    }

    protected PagePageVO pageEntityToPagePageVO(PageEntity pageEntity) {
        if ( pageEntity == null ) {
            return null;
        }

        PagePageVO pagePageVO = new PagePageVO();

        pagePageVO.setId( pageEntity.getId() );
        pagePageVO.setStatus( pageEntity.getStatus() );
        pagePageVO.setRemark( pageEntity.getRemark() );
        pagePageVO.setCreator( pageEntity.getCreator() );
        pagePageVO.setUpdater( pageEntity.getUpdater() );
        pagePageVO.setCreateTime( pageEntity.getCreateTime() );
        pagePageVO.setUpdateTime( pageEntity.getUpdateTime() );
        pagePageVO.setIsDeleted( pageEntity.getIsDeleted() );
        pagePageVO.setTenantId( pageEntity.getTenantId() );
        pagePageVO.setVersion( pageEntity.getVersion() );
        pagePageVO.setMenuId( pageEntity.getMenuId() );
        pagePageVO.setOptions( pageEntity.getOptions() );

        return pagePageVO;
    }

    protected List<PagePageVO> pageEntityListToPagePageVOList(List<PageEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PagePageVO> list1 = new ArrayList<PagePageVO>( list.size() );
        for ( PageEntity pageEntity : list ) {
            list1.add( pageEntityToPagePageVO( pageEntity ) );
        }

        return list1;
    }

    protected PageListVO pageEntityToPageListVO(PageEntity pageEntity) {
        if ( pageEntity == null ) {
            return null;
        }

        PageListVO pageListVO = new PageListVO();

        pageListVO.setId( pageEntity.getId() );
        pageListVO.setStatus( pageEntity.getStatus() );
        pageListVO.setRemark( pageEntity.getRemark() );
        pageListVO.setCreator( pageEntity.getCreator() );
        pageListVO.setUpdater( pageEntity.getUpdater() );
        pageListVO.setCreateTime( pageEntity.getCreateTime() );
        pageListVO.setUpdateTime( pageEntity.getUpdateTime() );
        pageListVO.setIsDeleted( pageEntity.getIsDeleted() );
        pageListVO.setTenantId( pageEntity.getTenantId() );
        pageListVO.setVersion( pageEntity.getVersion() );
        pageListVO.setMenuId( pageEntity.getMenuId() );
        pageListVO.setOptions( pageEntity.getOptions() );

        return pageListVO;
    }

    protected PageExportVO pageEntityToPageExportVO(PageEntity pageEntity) {
        if ( pageEntity == null ) {
            return null;
        }

        PageExportVO pageExportVO = new PageExportVO();

        pageExportVO.setStatus( pageEntity.getStatus() );
        pageExportVO.setRemark( pageEntity.getRemark() );
        pageExportVO.setCreator( pageEntity.getCreator() );
        pageExportVO.setUpdater( pageEntity.getUpdater() );
        pageExportVO.setCreateTime( pageEntity.getCreateTime() );
        pageExportVO.setUpdateTime( pageEntity.getUpdateTime() );
        pageExportVO.setMenuId( pageEntity.getMenuId() );
        pageExportVO.setOptions( pageEntity.getOptions() );

        return pageExportVO;
    }

    protected PageSaveDTO pageExportVOToPageSaveDTO(PageExportVO pageExportVO) {
        if ( pageExportVO == null ) {
            return null;
        }

        PageSaveDTO pageSaveDTO = new PageSaveDTO();

        pageSaveDTO.setStatus( pageExportVO.getStatus() );
        pageSaveDTO.setCreator( pageExportVO.getCreator() );
        pageSaveDTO.setUpdater( pageExportVO.getUpdater() );
        pageSaveDTO.setRemark( pageExportVO.getRemark() );
        pageSaveDTO.setCreateTime( pageExportVO.getCreateTime() );
        pageSaveDTO.setUpdateTime( pageExportVO.getUpdateTime() );
        pageSaveDTO.setMenuId( pageExportVO.getMenuId() );
        pageSaveDTO.setOptions( pageExportVO.getOptions() );

        return pageSaveDTO;
    }
}
