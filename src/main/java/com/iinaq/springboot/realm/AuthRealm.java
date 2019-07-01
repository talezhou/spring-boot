package com.iinaq.springboot.realm;

import com.iinaq.springboot.db.DbCache;
import com.iinaq.springboot.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName AuthRealm
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  15:54
 **/
@Configuration
public class AuthRealm extends AuthorizingRealm {
    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        User user = Optional.ofNullable(DbCache.USERS_CACHE.get(principal)).orElseThrow(UnknownAccountException::new);
        if (!user.isLocked()){
            throw  new LockedAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, user.getPassword(),getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION",user );
        return simpleAuthenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User)session.getAttribute("USER_SESSION");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        HashSet<String> roles = new HashSet<>();
        roles.add(user.getRoleName());
        simpleAuthorizationInfo.setRoles(roles);
        final Map<String, Collection<String>> permissionsCache = DbCache.PERMISSIONS_CACHE;
        Collection<String> permissions = permissionsCache.get(user.getRoleName());
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
