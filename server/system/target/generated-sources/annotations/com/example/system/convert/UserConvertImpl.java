package com.example.system.convert;

import com.example.framework.common.PageList;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.UserBaseInfoVO;
import com.example.system.dal.vo.user.UserDetailVO;
import com.example.system.dal.vo.user.UserExportVO;
import com.example.system.dal.vo.user.UserListVO;
import com.example.system.dal.vo.user.UserPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-26T11:27:13+0800",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public PageList<UserPageVO> page(PageList<UserEntity> user) {
        if ( user == null ) {
            return null;
        }

        PageList<UserPageVO> pageList = new PageList<UserPageVO>();

        pageList.setPage( user.getPage() );
        pageList.setTotal( user.getTotal() );
        pageList.setSize( user.getSize() );
        pageList.setList( userEntityListToUserPageVOList( user.getList() ) );

        return pageList;
    }

    @Override
    public List<UserListVO> list(List<UserEntity> user) {
        if ( user == null ) {
            return null;
        }

        List<UserListVO> list = new ArrayList<UserListVO>( user.size() );
        for ( UserEntity userEntity : user ) {
            list.add( userEntityToUserListVO( userEntity ) );
        }

        return list;
    }

    @Override
    public UserDetailVO detail(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDetailVO userDetailVO = new UserDetailVO();

        userDetailVO.setId( user.getId() );
        userDetailVO.setStatus( user.getStatus() );
        userDetailVO.setRemark( user.getRemark() );
        userDetailVO.setCreator( user.getCreator() );
        userDetailVO.setUpdater( user.getUpdater() );
        userDetailVO.setCreateTime( user.getCreateTime() );
        userDetailVO.setUpdateTime( user.getUpdateTime() );
        userDetailVO.setIsDeleted( user.getIsDeleted() );
        userDetailVO.setTenantId( user.getTenantId() );
        userDetailVO.setVersion( user.getVersion() );
        userDetailVO.setUid( user.getUid() );
        userDetailVO.setPhone( user.getPhone() );
        userDetailVO.setEmail( user.getEmail() );
        userDetailVO.setAccount( user.getAccount() );
        userDetailVO.setUser( user.getUser() );
        userDetailVO.setPassword( user.getPassword() );
        userDetailVO.setNickname( user.getNickname() );
        userDetailVO.setAvatar( user.getAvatar() );
        userDetailVO.setUserType( user.getUserType() );

        return userDetailVO;
    }

    @Override
    public UserEntity save(UserSaveDTO user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setStatus( user.getStatus() );
        userEntity.setCreator( user.getCreator() );
        userEntity.setUpdater( user.getUpdater() );
        userEntity.setRemark( user.getRemark() );
        userEntity.setCreateTime( user.getCreateTime() );
        userEntity.setUpdateTime( user.getUpdateTime() );
        userEntity.setIsDeleted( user.getIsDeleted() );
        userEntity.setTenantId( user.getTenantId() );
        userEntity.setVersion( user.getVersion() );
        userEntity.setUid( user.getUid() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setAccount( user.getAccount() );
        userEntity.setUser( user.getUser() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setNickname( user.getNickname() );
        userEntity.setAvatar( user.getAvatar() );
        userEntity.setUserType( user.getUserType() );

        return userEntity;
    }

    @Override
    public List<UserEntity> saveList(List<UserSaveDTO> user) {
        if ( user == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( user.size() );
        for ( UserSaveDTO userSaveDTO : user ) {
            list.add( save( userSaveDTO ) );
        }

        return list;
    }

    @Override
    public List<UserExportVO> export(List<UserEntity> user) {
        if ( user == null ) {
            return null;
        }

        List<UserExportVO> list = new ArrayList<UserExportVO>( user.size() );
        for ( UserEntity userEntity : user ) {
            list.add( userEntityToUserExportVO( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserSaveDTO> imports(List<UserExportVO> user) {
        if ( user == null ) {
            return null;
        }

        List<UserSaveDTO> list = new ArrayList<UserSaveDTO>( user.size() );
        for ( UserExportVO userExportVO : user ) {
            list.add( userExportVOToUserSaveDTO( userExportVO ) );
        }

        return list;
    }

    @Override
    public UserBaseInfoVO UserBaseInfoVO(UserEntity users) {
        if ( users == null ) {
            return null;
        }

        UserBaseInfoVO userBaseInfoVO = new UserBaseInfoVO();

        userBaseInfoVO.setId( users.getId() );
        if ( users.getUid() != null ) {
            userBaseInfoVO.setUid( String.valueOf( users.getUid() ) );
        }
        userBaseInfoVO.setPhone( users.getPhone() );
        userBaseInfoVO.setEmail( users.getEmail() );
        userBaseInfoVO.setAccount( users.getAccount() );
        userBaseInfoVO.setUser( users.getUser() );
        userBaseInfoVO.setAvatar( users.getAvatar() );
        userBaseInfoVO.setUserType( users.getUserType() );

        return userBaseInfoVO;
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

    protected UserSaveDTO userExportVOToUserSaveDTO(UserExportVO userExportVO) {
        if ( userExportVO == null ) {
            return null;
        }

        UserSaveDTO userSaveDTO = new UserSaveDTO();

        userSaveDTO.setStatus( userExportVO.getStatus() );
        userSaveDTO.setCreator( userExportVO.getCreator() );
        userSaveDTO.setUpdater( userExportVO.getUpdater() );
        userSaveDTO.setRemark( userExportVO.getRemark() );
        userSaveDTO.setCreateTime( userExportVO.getCreateTime() );
        userSaveDTO.setUpdateTime( userExportVO.getUpdateTime() );
        userSaveDTO.setUid( userExportVO.getUid() );
        userSaveDTO.setPhone( userExportVO.getPhone() );
        userSaveDTO.setEmail( userExportVO.getEmail() );
        userSaveDTO.setAccount( userExportVO.getAccount() );
        userSaveDTO.setUser( userExportVO.getUser() );
        userSaveDTO.setPassword( userExportVO.getPassword() );
        userSaveDTO.setNickname( userExportVO.getNickname() );
        userSaveDTO.setAvatar( userExportVO.getAvatar() );
        userSaveDTO.setUserType( userExportVO.getUserType() );

        return userSaveDTO;
    }
}
