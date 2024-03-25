package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.role.RoleSaveDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.vo.role.RoleDetailVO;
import com.example.system.dal.vo.role.RoleExportVO;
import com.example.system.dal.vo.role.RoleListVO;
import com.example.system.dal.vo.role.RolePageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-25T19:53:08+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class RoleConvertImpl implements RoleConvert {

    @Override
    public PageList<RolePageVO> page(PageList<RoleEntity> role) {
        if ( role == null ) {
            return null;
        }

        PageList<RolePageVO> pageList = new PageList<RolePageVO>();

        pageList.setPage( role.getPage() );
        pageList.setTotal( role.getTotal() );
        pageList.setSize( role.getSize() );
        pageList.setList( roleEntityListToRolePageVOList( role.getList() ) );

        return pageList;
    }

    @Override
    public List<RoleListVO> list(List<RoleEntity> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleListVO> list = new ArrayList<RoleListVO>( role.size() );
        for ( RoleEntity roleEntity : role ) {
            list.add( roleEntityToRoleListVO( roleEntity ) );
        }

        return list;
    }

    @Override
    public RoleDetailVO detail(RoleEntity role) {
        if ( role == null ) {
            return null;
        }

        RoleDetailVO roleDetailVO = new RoleDetailVO();

        roleDetailVO.setId( role.getId() );
        roleDetailVO.setStatus( role.getStatus() );
        roleDetailVO.setRemark( role.getRemark() );
        roleDetailVO.setCreator( role.getCreator() );
        roleDetailVO.setUpdater( role.getUpdater() );
        roleDetailVO.setCreateTime( role.getCreateTime() );
        roleDetailVO.setUpdateTime( role.getUpdateTime() );
        roleDetailVO.setIsDeleted( role.getIsDeleted() );
        roleDetailVO.setTenantId( role.getTenantId() );
        roleDetailVO.setVersion( role.getVersion() );
        roleDetailVO.setRoleName( role.getRoleName() );
        roleDetailVO.setRoleCode( role.getRoleCode() );

        return roleDetailVO;
    }

    @Override
    public RoleEntity save(RoleSaveDTO role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( role.getId() );
        roleEntity.setStatus( role.getStatus() );
        roleEntity.setCreator( role.getCreator() );
        roleEntity.setUpdater( role.getUpdater() );
        roleEntity.setRemark( role.getRemark() );
        roleEntity.setCreateTime( role.getCreateTime() );
        roleEntity.setUpdateTime( role.getUpdateTime() );
        roleEntity.setIsDeleted( role.getIsDeleted() );
        roleEntity.setTenantId( role.getTenantId() );
        roleEntity.setVersion( role.getVersion() );
        roleEntity.setRoleName( role.getRoleName() );
        roleEntity.setRoleCode( role.getRoleCode() );

        return roleEntity;
    }

    @Override
    public List<RoleEntity> saveList(List<RoleSaveDTO> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleEntity> list = new ArrayList<RoleEntity>( role.size() );
        for ( RoleSaveDTO roleSaveDTO : role ) {
            list.add( save( roleSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleExportVO> export(List<RoleEntity> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleExportVO> list = new ArrayList<RoleExportVO>( role.size() );
        for ( RoleEntity roleEntity : role ) {
            list.add( roleEntityToRoleExportVO( roleEntity ) );
        }

        return list;
    }

    @Override
    public List<RoleSaveDTO> imports(List<RoleExportVO> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleSaveDTO> list = new ArrayList<RoleSaveDTO>( role.size() );
        for ( RoleExportVO roleExportVO : role ) {
            list.add( roleExportVOToRoleSaveDTO( roleExportVO ) );
        }

        return list;
    }

    protected RolePageVO roleEntityToRolePageVO(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RolePageVO rolePageVO = new RolePageVO();

        rolePageVO.setId( roleEntity.getId() );
        rolePageVO.setStatus( roleEntity.getStatus() );
        rolePageVO.setRemark( roleEntity.getRemark() );
        rolePageVO.setCreator( roleEntity.getCreator() );
        rolePageVO.setUpdater( roleEntity.getUpdater() );
        rolePageVO.setCreateTime( roleEntity.getCreateTime() );
        rolePageVO.setUpdateTime( roleEntity.getUpdateTime() );
        rolePageVO.setIsDeleted( roleEntity.getIsDeleted() );
        rolePageVO.setTenantId( roleEntity.getTenantId() );
        rolePageVO.setVersion( roleEntity.getVersion() );
        rolePageVO.setRoleName( roleEntity.getRoleName() );
        rolePageVO.setRoleCode( roleEntity.getRoleCode() );

        return rolePageVO;
    }

    protected List<RolePageVO> roleEntityListToRolePageVOList(List<RoleEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<RolePageVO> list1 = new ArrayList<RolePageVO>( list.size() );
        for ( RoleEntity roleEntity : list ) {
            list1.add( roleEntityToRolePageVO( roleEntity ) );
        }

        return list1;
    }

    protected RoleListVO roleEntityToRoleListVO(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleListVO roleListVO = new RoleListVO();

        roleListVO.setId( roleEntity.getId() );
        roleListVO.setStatus( roleEntity.getStatus() );
        roleListVO.setRemark( roleEntity.getRemark() );
        roleListVO.setCreator( roleEntity.getCreator() );
        roleListVO.setUpdater( roleEntity.getUpdater() );
        roleListVO.setCreateTime( roleEntity.getCreateTime() );
        roleListVO.setUpdateTime( roleEntity.getUpdateTime() );
        roleListVO.setIsDeleted( roleEntity.getIsDeleted() );
        roleListVO.setTenantId( roleEntity.getTenantId() );
        roleListVO.setVersion( roleEntity.getVersion() );
        roleListVO.setRoleName( roleEntity.getRoleName() );
        roleListVO.setRoleCode( roleEntity.getRoleCode() );

        return roleListVO;
    }

    protected RoleExportVO roleEntityToRoleExportVO(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleExportVO roleExportVO = new RoleExportVO();

        roleExportVO.setStatus( roleEntity.getStatus() );
        roleExportVO.setRemark( roleEntity.getRemark() );
        roleExportVO.setCreator( roleEntity.getCreator() );
        roleExportVO.setUpdater( roleEntity.getUpdater() );
        roleExportVO.setCreateTime( roleEntity.getCreateTime() );
        roleExportVO.setUpdateTime( roleEntity.getUpdateTime() );
        roleExportVO.setRoleName( roleEntity.getRoleName() );
        roleExportVO.setRoleCode( roleEntity.getRoleCode() );

        return roleExportVO;
    }

    protected RoleSaveDTO roleExportVOToRoleSaveDTO(RoleExportVO roleExportVO) {
        if ( roleExportVO == null ) {
            return null;
        }

        RoleSaveDTO roleSaveDTO = new RoleSaveDTO();

        roleSaveDTO.setStatus( roleExportVO.getStatus() );
        roleSaveDTO.setCreator( roleExportVO.getCreator() );
        roleSaveDTO.setUpdater( roleExportVO.getUpdater() );
        roleSaveDTO.setRemark( roleExportVO.getRemark() );
        roleSaveDTO.setCreateTime( roleExportVO.getCreateTime() );
        roleSaveDTO.setUpdateTime( roleExportVO.getUpdateTime() );
        roleSaveDTO.setRoleName( roleExportVO.getRoleName() );
        roleSaveDTO.setRoleCode( roleExportVO.getRoleCode() );

        return roleSaveDTO;
    }
}
