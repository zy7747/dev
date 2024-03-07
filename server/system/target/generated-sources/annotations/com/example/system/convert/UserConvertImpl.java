package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.UserDetailVO;
import com.example.system.dal.vo.user.UserExportVO;
import com.example.system.dal.vo.user.UserListVO;
import com.example.system.dal.vo.user.UserPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T11:40:09+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public PageList<UserPageVO> page(PageList<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        PageList<UserPageVO> pageList = new PageList<UserPageVO>();

        pageList.setPage( users.getPage() );
        pageList.setTotal( users.getTotal() );
        pageList.setSize( users.getSize() );
        pageList.setList( userEntityListToUserPageVOList( users.getList() ) );

        return pageList;
    }

    @Override
    public List<UserListVO> list(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserListVO> list = new ArrayList<UserListVO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( userEntityToUserListVO( userEntity ) );
        }

        return list;
    }

    @Override
    public UserDetailVO detail(UserEntity users) {
        if ( users == null ) {
            return null;
        }

        UserDetailVO userDetailVO = new UserDetailVO();

        userDetailVO.setId( users.getId() );
        userDetailVO.setStatus( users.getStatus() );
        userDetailVO.setRemark( users.getRemark() );
        userDetailVO.setCreator( users.getCreator() );
        userDetailVO.setUpdater( users.getUpdater() );
        userDetailVO.setCreateTime( users.getCreateTime() );
        userDetailVO.setUpdateTime( users.getUpdateTime() );
        userDetailVO.setIsDeleted( users.getIsDeleted() );
        userDetailVO.setTenantId( users.getTenantId() );
        userDetailVO.setVersion( users.getVersion() );
        userDetailVO.setUid( users.getUid() );
        userDetailVO.setPhone( users.getPhone() );
        userDetailVO.setEmail( users.getEmail() );
        userDetailVO.setAccount( users.getAccount() );
        userDetailVO.setUser( users.getUser() );
        userDetailVO.setPassword( users.getPassword() );
        userDetailVO.setNickname( users.getNickname() );
        userDetailVO.setAvatar( users.getAvatar() );
        userDetailVO.setUserType( users.getUserType() );

        return userDetailVO;
    }

    @Override
    public UserEntity save(UserSaveDTO users) {
        if ( users == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( users.getId() );
        userEntity.setStatus( users.getStatus() );
        userEntity.setCreator( users.getCreator() );
        userEntity.setUpdater( users.getUpdater() );
        userEntity.setRemark( users.getRemark() );
        userEntity.setCreateTime( users.getCreateTime() );
        userEntity.setUpdateTime( users.getUpdateTime() );
        userEntity.setIsDeleted( users.getIsDeleted() );
        userEntity.setTenantId( users.getTenantId() );
        userEntity.setVersion( users.getVersion() );
        userEntity.setUid( users.getUid() );
        userEntity.setPhone( users.getPhone() );
        userEntity.setEmail( users.getEmail() );
        userEntity.setAccount( users.getAccount() );
        userEntity.setUser( users.getUser() );
        userEntity.setPassword( users.getPassword() );
        userEntity.setNickname( users.getNickname() );
        userEntity.setAvatar( users.getAvatar() );
        userEntity.setUserType( users.getUserType() );

        return userEntity;
    }

    @Override
    public List<UserEntity> saveList(List<UserSaveDTO> users) {
        if ( users == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( users.size() );
        for ( UserSaveDTO userSaveDTO : users ) {
            list.add( save( userSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<UserExportVO> export(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserExportVO> list = new ArrayList<UserExportVO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( userEntityToUserExportVO( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> imports(List<UserExportVO> users) {
        if ( users == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( users.size() );
        for ( UserExportVO userExportVO : users ) {
            list.add( userExportVOToUserEntity( userExportVO ) );
        }

        return list;
    }

    protected UserPageVO userEntityToUserPageVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserPageVO userPageVO = new UserPageVO();

        userPageVO.setId( userEntity.getId() );
        userPageVO.setStatus( userEntity.getStatus() );
        userPageVO.setRemark( userEntity.getRemark() );
        userPageVO.setCreator( userEntity.getCreator() );
        userPageVO.setUpdater( userEntity.getUpdater() );
        userPageVO.setCreateTime( userEntity.getCreateTime() );
        userPageVO.setUpdateTime( userEntity.getUpdateTime() );
        userPageVO.setIsDeleted( userEntity.getIsDeleted() );
        userPageVO.setTenantId( userEntity.getTenantId() );
        userPageVO.setVersion( userEntity.getVersion() );
        userPageVO.setUid( userEntity.getUid() );
        userPageVO.setPhone( userEntity.getPhone() );
        userPageVO.setEmail( userEntity.getEmail() );
        userPageVO.setAccount( userEntity.getAccount() );
        userPageVO.setUser( userEntity.getUser() );
        userPageVO.setPassword( userEntity.getPassword() );
        userPageVO.setNickname( userEntity.getNickname() );
        userPageVO.setAvatar( userEntity.getAvatar() );
        userPageVO.setUserType( userEntity.getUserType() );

        return userPageVO;
    }

    protected List<UserPageVO> userEntityListToUserPageVOList(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<UserPageVO> list1 = new ArrayList<UserPageVO>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( userEntityToUserPageVO( userEntity ) );
        }

        return list1;
    }

    protected UserListVO userEntityToUserListVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserListVO userListVO = new UserListVO();

        userListVO.setId( userEntity.getId() );
        userListVO.setStatus( userEntity.getStatus() );
        userListVO.setRemark( userEntity.getRemark() );
        userListVO.setCreator( userEntity.getCreator() );
        userListVO.setUpdater( userEntity.getUpdater() );
        userListVO.setCreateTime( userEntity.getCreateTime() );
        userListVO.setUpdateTime( userEntity.getUpdateTime() );
        userListVO.setIsDeleted( userEntity.getIsDeleted() );
        userListVO.setTenantId( userEntity.getTenantId() );
        userListVO.setVersion( userEntity.getVersion() );
        userListVO.setUid( userEntity.getUid() );
        userListVO.setPhone( userEntity.getPhone() );
        userListVO.setEmail( userEntity.getEmail() );
        userListVO.setAccount( userEntity.getAccount() );
        userListVO.setUser( userEntity.getUser() );
        userListVO.setPassword( userEntity.getPassword() );
        userListVO.setNickname( userEntity.getNickname() );
        userListVO.setAvatar( userEntity.getAvatar() );
        userListVO.setUserType( userEntity.getUserType() );

        return userListVO;
    }

    protected UserExportVO userEntityToUserExportVO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserExportVO userExportVO = new UserExportVO();

        userExportVO.setStatus( userEntity.getStatus() );
        userExportVO.setRemark( userEntity.getRemark() );
        userExportVO.setCreator( userEntity.getCreator() );
        userExportVO.setUpdater( userEntity.getUpdater() );
        userExportVO.setCreateTime( userEntity.getCreateTime() );
        userExportVO.setUpdateTime( userEntity.getUpdateTime() );
        userExportVO.setUid( userEntity.getUid() );
        userExportVO.setPhone( userEntity.getPhone() );
        userExportVO.setEmail( userEntity.getEmail() );
        userExportVO.setAccount( userEntity.getAccount() );
        userExportVO.setUser( userEntity.getUser() );
        userExportVO.setPassword( userEntity.getPassword() );
        userExportVO.setNickname( userEntity.getNickname() );
        userExportVO.setAvatar( userEntity.getAvatar() );
        userExportVO.setUserType( userEntity.getUserType() );

        return userExportVO;
    }

    protected UserEntity userExportVOToUserEntity(UserExportVO userExportVO) {
        if ( userExportVO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setStatus( userExportVO.getStatus() );
        userEntity.setCreator( userExportVO.getCreator() );
        userEntity.setUpdater( userExportVO.getUpdater() );
        userEntity.setRemark( userExportVO.getRemark() );
        userEntity.setCreateTime( userExportVO.getCreateTime() );
        userEntity.setUpdateTime( userExportVO.getUpdateTime() );
        userEntity.setUid( userExportVO.getUid() );
        userEntity.setPhone( userExportVO.getPhone() );
        userEntity.setEmail( userExportVO.getEmail() );
        userEntity.setAccount( userExportVO.getAccount() );
        userEntity.setUser( userExportVO.getUser() );
        userEntity.setPassword( userExportVO.getPassword() );
        userEntity.setNickname( userExportVO.getNickname() );
        userEntity.setAvatar( userExportVO.getAvatar() );
        userEntity.setUserType( userExportVO.getUserType() );

        return userEntity;
    }
}
