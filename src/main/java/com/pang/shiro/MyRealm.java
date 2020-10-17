package com.pang.shiro;

import com.pang.entity.User;
import com.pang.jwt.JwtToken;
import com.pang.mapper.PermissionMapper;
import com.pang.mapper.RoleMapper;
import com.pang.mapper.UserMapper;
import com.pang.service.UserService;
import com.pang.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * MyRealm:自定义一个授权
 *
 * @author pangjian
 * @date: 2020/10/16
 */

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserService userService;


    //判断左边的对象是否属于右边类的实例，返回Boolean类型值
    //该方法判断令牌是否被支持
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsername(principalCollection.toString());

        User user = userService.findByUserName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user != null){
            System.out.println("授权过此");
            Set<String> roleName = roleMapper.queryRoleNamesByUsername(username);
            Set<String> ps = permissionMapper.queryPermissionByUsername(username);
            //获取用户的用户名，因为认证成功可以从认证消息中拿出来
            //String username = (String) principalCollection.iterator().next();
            //根据用户名查询当前用户的角色列表
            //Set<String> roleName = roleMapper.queryRoleNamesByUsername(username);
            //根据用户名查询当前用户的权限列表
            //Set<String> ps = permissionMapper.queryPermissionByUsername(username);
            //封装
            //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(roleName);
            info.setStringPermissions(ps);

        }
        return info;
    }
    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        System.out.println(token);
        // 解密获得username，用于和数据库进行对比
        String username = null;
        try {
            //这里工具类没有处理空指针等异常这里处理一下(这里处理科学一些)
            username = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("heard的token拼写错误或者值为空");
        }
        if (username == null) {
          log.error("token无效(空''或者null都不行!)");
            throw new AuthenticationException("token无效");
        }
        User userBean = userService.findByUserName(username);
        if (userBean == null) {
            log.error("用户不存在!)");
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, username, userBean.getPassword())) {
            log.error("用户名或密码错误(token无效或者与登录者不匹配)!)");
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)!");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
