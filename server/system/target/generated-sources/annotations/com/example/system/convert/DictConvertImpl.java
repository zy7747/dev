package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.dict.DictSaveDTO;
import com.example.system.dal.entity.DictEntity;
import com.example.system.dal.vo.dict.DictDetailVO;
import com.example.system.dal.vo.dict.DictExportVO;
import com.example.system.dal.vo.dict.DictListVO;
import com.example.system.dal.vo.dict.DictPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-22T16:09:09+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class DictConvertImpl implements DictConvert {

    @Override
    public PageList<DictPageVO> page(PageList<DictEntity> dict) {
        if ( dict == null ) {
            return null;
        }

        PageList<DictPageVO> pageList = new PageList<DictPageVO>();

        pageList.setPage( dict.getPage() );
        pageList.setTotal( dict.getTotal() );
        pageList.setSize( dict.getSize() );
        pageList.setList( dictEntityListToDictPageVOList( dict.getList() ) );

        return pageList;
    }

    @Override
    public List<DictListVO> list(List<DictEntity> dict) {
        if ( dict == null ) {
            return null;
        }

        List<DictListVO> list = new ArrayList<DictListVO>( dict.size() );
        for ( DictEntity dictEntity : dict ) {
            list.add( dictEntityToDictListVO( dictEntity ) );
        }

        return list;
    }

    @Override
    public DictDetailVO detail(DictEntity dict) {
        if ( dict == null ) {
            return null;
        }

        DictDetailVO dictDetailVO = new DictDetailVO();

        dictDetailVO.setId( dict.getId() );
        dictDetailVO.setStatus( dict.getStatus() );
        dictDetailVO.setRemark( dict.getRemark() );
        dictDetailVO.setCreator( dict.getCreator() );
        dictDetailVO.setUpdater( dict.getUpdater() );
        dictDetailVO.setCreateTime( dict.getCreateTime() );
        dictDetailVO.setUpdateTime( dict.getUpdateTime() );
        dictDetailVO.setIsDeleted( dict.getIsDeleted() );
        dictDetailVO.setTenantId( dict.getTenantId() );
        dictDetailVO.setVersion( dict.getVersion() );
        dictDetailVO.setParentId( dict.getParentId() );
        dictDetailVO.setDictName( dict.getDictName() );
        dictDetailVO.setDictCode( dict.getDictCode() );
        dictDetailVO.setDictType( dict.getDictType() );
        dictDetailVO.setLabel( dict.getLabel() );
        dictDetailVO.setValue( dict.getValue() );
        dictDetailVO.setColor( dict.getColor() );
        dictDetailVO.setSort( dict.getSort() );
        dictDetailVO.setParams( dict.getParams() );

        return dictDetailVO;
    }

    @Override
    public DictEntity save(DictSaveDTO dict) {
        if ( dict == null ) {
            return null;
        }

        DictEntity dictEntity = new DictEntity();

        dictEntity.setId( dict.getId() );
        dictEntity.setStatus( dict.getStatus() );
        dictEntity.setCreator( dict.getCreator() );
        dictEntity.setUpdater( dict.getUpdater() );
        dictEntity.setRemark( dict.getRemark() );
        dictEntity.setCreateTime( dict.getCreateTime() );
        dictEntity.setUpdateTime( dict.getUpdateTime() );
        dictEntity.setIsDeleted( dict.getIsDeleted() );
        dictEntity.setTenantId( dict.getTenantId() );
        dictEntity.setVersion( dict.getVersion() );
        dictEntity.setParentId( dict.getParentId() );
        dictEntity.setDictName( dict.getDictName() );
        dictEntity.setDictCode( dict.getDictCode() );
        dictEntity.setDictType( dict.getDictType() );
        dictEntity.setLabel( dict.getLabel() );
        dictEntity.setValue( dict.getValue() );
        dictEntity.setColor( dict.getColor() );
        dictEntity.setSort( dict.getSort() );
        dictEntity.setParams( dict.getParams() );

        return dictEntity;
    }

    @Override
    public List<DictEntity> saveList(List<DictSaveDTO> dict) {
        if ( dict == null ) {
            return null;
        }

        List<DictEntity> list = new ArrayList<DictEntity>( dict.size() );
        for ( DictSaveDTO dictSaveDTO : dict ) {
            list.add( save( dictSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<DictExportVO> export(List<DictEntity> dict) {
        if ( dict == null ) {
            return null;
        }

        List<DictExportVO> list = new ArrayList<DictExportVO>( dict.size() );
        for ( DictEntity dictEntity : dict ) {
            list.add( dictEntityToDictExportVO( dictEntity ) );
        }

        return list;
    }

    @Override
    public List<DictSaveDTO> imports(List<DictExportVO> dict) {
        if ( dict == null ) {
            return null;
        }

        List<DictSaveDTO> list = new ArrayList<DictSaveDTO>( dict.size() );
        for ( DictExportVO dictExportVO : dict ) {
            list.add( dictExportVOToDictSaveDTO( dictExportVO ) );
        }

        return list;
    }

    protected DictPageVO dictEntityToDictPageVO(DictEntity dictEntity) {
        if ( dictEntity == null ) {
            return null;
        }

        DictPageVO dictPageVO = new DictPageVO();

        dictPageVO.setId( dictEntity.getId() );
        dictPageVO.setStatus( dictEntity.getStatus() );
        dictPageVO.setRemark( dictEntity.getRemark() );
        dictPageVO.setCreator( dictEntity.getCreator() );
        dictPageVO.setUpdater( dictEntity.getUpdater() );
        dictPageVO.setCreateTime( dictEntity.getCreateTime() );
        dictPageVO.setUpdateTime( dictEntity.getUpdateTime() );
        dictPageVO.setIsDeleted( dictEntity.getIsDeleted() );
        dictPageVO.setTenantId( dictEntity.getTenantId() );
        dictPageVO.setVersion( dictEntity.getVersion() );
        dictPageVO.setParentId( dictEntity.getParentId() );
        dictPageVO.setDictName( dictEntity.getDictName() );
        dictPageVO.setDictCode( dictEntity.getDictCode() );
        dictPageVO.setDictType( dictEntity.getDictType() );
        dictPageVO.setLabel( dictEntity.getLabel() );
        dictPageVO.setValue( dictEntity.getValue() );
        dictPageVO.setColor( dictEntity.getColor() );
        dictPageVO.setSort( dictEntity.getSort() );
        dictPageVO.setParams( dictEntity.getParams() );

        return dictPageVO;
    }

    protected List<DictPageVO> dictEntityListToDictPageVOList(List<DictEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<DictPageVO> list1 = new ArrayList<DictPageVO>( list.size() );
        for ( DictEntity dictEntity : list ) {
            list1.add( dictEntityToDictPageVO( dictEntity ) );
        }

        return list1;
    }

    protected DictListVO dictEntityToDictListVO(DictEntity dictEntity) {
        if ( dictEntity == null ) {
            return null;
        }

        DictListVO dictListVO = new DictListVO();

        dictListVO.setId( dictEntity.getId() );
        dictListVO.setStatus( dictEntity.getStatus() );
        dictListVO.setRemark( dictEntity.getRemark() );
        dictListVO.setCreator( dictEntity.getCreator() );
        dictListVO.setUpdater( dictEntity.getUpdater() );
        dictListVO.setCreateTime( dictEntity.getCreateTime() );
        dictListVO.setUpdateTime( dictEntity.getUpdateTime() );
        dictListVO.setIsDeleted( dictEntity.getIsDeleted() );
        dictListVO.setTenantId( dictEntity.getTenantId() );
        dictListVO.setVersion( dictEntity.getVersion() );
        dictListVO.setParentId( dictEntity.getParentId() );
        dictListVO.setDictName( dictEntity.getDictName() );
        dictListVO.setDictCode( dictEntity.getDictCode() );
        dictListVO.setDictType( dictEntity.getDictType() );
        dictListVO.setLabel( dictEntity.getLabel() );
        dictListVO.setValue( dictEntity.getValue() );
        dictListVO.setColor( dictEntity.getColor() );
        dictListVO.setSort( dictEntity.getSort() );
        dictListVO.setParams( dictEntity.getParams() );

        return dictListVO;
    }

    protected DictExportVO dictEntityToDictExportVO(DictEntity dictEntity) {
        if ( dictEntity == null ) {
            return null;
        }

        DictExportVO dictExportVO = new DictExportVO();

        dictExportVO.setStatus( dictEntity.getStatus() );
        dictExportVO.setRemark( dictEntity.getRemark() );
        dictExportVO.setCreator( dictEntity.getCreator() );
        dictExportVO.setUpdater( dictEntity.getUpdater() );
        dictExportVO.setCreateTime( dictEntity.getCreateTime() );
        dictExportVO.setUpdateTime( dictEntity.getUpdateTime() );
        dictExportVO.setParentId( dictEntity.getParentId() );
        dictExportVO.setDictName( dictEntity.getDictName() );
        dictExportVO.setDictCode( dictEntity.getDictCode() );
        dictExportVO.setDictType( dictEntity.getDictType() );
        dictExportVO.setLabel( dictEntity.getLabel() );
        dictExportVO.setValue( dictEntity.getValue() );
        dictExportVO.setColor( dictEntity.getColor() );
        dictExportVO.setSort( dictEntity.getSort() );
        dictExportVO.setParams( dictEntity.getParams() );

        return dictExportVO;
    }

    protected DictSaveDTO dictExportVOToDictSaveDTO(DictExportVO dictExportVO) {
        if ( dictExportVO == null ) {
            return null;
        }

        DictSaveDTO dictSaveDTO = new DictSaveDTO();

        dictSaveDTO.setStatus( dictExportVO.getStatus() );
        dictSaveDTO.setCreator( dictExportVO.getCreator() );
        dictSaveDTO.setUpdater( dictExportVO.getUpdater() );
        dictSaveDTO.setRemark( dictExportVO.getRemark() );
        dictSaveDTO.setCreateTime( dictExportVO.getCreateTime() );
        dictSaveDTO.setUpdateTime( dictExportVO.getUpdateTime() );
        dictSaveDTO.setParentId( dictExportVO.getParentId() );
        dictSaveDTO.setDictName( dictExportVO.getDictName() );
        dictSaveDTO.setDictCode( dictExportVO.getDictCode() );
        dictSaveDTO.setDictType( dictExportVO.getDictType() );
        dictSaveDTO.setLabel( dictExportVO.getLabel() );
        dictSaveDTO.setValue( dictExportVO.getValue() );
        dictSaveDTO.setColor( dictExportVO.getColor() );
        dictSaveDTO.setSort( dictExportVO.getSort() );
        dictSaveDTO.setParams( dictExportVO.getParams() );

        return dictSaveDTO;
    }
}
