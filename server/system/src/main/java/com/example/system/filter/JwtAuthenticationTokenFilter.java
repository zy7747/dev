package com.example.system.filter;

import com.example.system.dal.dto.permission.PermissionQueryDTO;
import com.example.system.dal.entity.PermissionEntity;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.entity.UserEntity;

import com.example.system.mapper.PermissionMapper;
import com.example.system.mapper.UserMapper;
import com.example.system.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    UserMapper userMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        long userId;

        //token拦截
        try {
            userId = Long.parseLong(JwtUtil.getTokenInfo(token).toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //获取用户信息
        UserEntity userInfo = userMapper.selectById(userId);
        // 获取用户角色
        List<RoleEntity> roles = userMapper.selectRole(userInfo.getId());

        //判断是否有admin角色
        boolean isAdmin = roles.stream().anyMatch(role -> "admin".equals(role.getRoleCode()));

        //用户拥有的权限列表
        List<GrantedAuthority> list = new ArrayList<>();

        if (isAdmin) {
            //获取全部权限
            List<PermissionEntity> permissionList = permissionMapper.queryList(new PermissionQueryDTO());

            permissionList.forEach(item -> list.add(new SimpleGrantedAuthority(item.getPermission())));
        } else {
            //获取用户权限列表
            List<PermissionEntity> permissionList = userMapper.selectPermission(userId);


            permissionList.forEach(item -> list.add(new SimpleGrantedAuthority(item.getPermission())));
        }

        //过滤权限
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userInfo.getUser(), userInfo.getPassword(), list);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
