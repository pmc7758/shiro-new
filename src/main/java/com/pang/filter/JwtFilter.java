package com.pang.filter;

import com.pang.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * JwtFilter:jwt过滤器来作为shiro的过滤器
 *
 * @author pangjian
 * @date: 2020/10/16
 */
@Slf4j
@Component//这个注入与否影响不大
public class JwtFilter extends BasicHttpAuthenticationFilter implements Filter {



    //看看用户的token认证是否是对的
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            //下面的方法会直接到自定义realm中，执行认证和授权
            getSubject(request, response).login(jwtToken);
            // 如果没有抛出异常则代表登入成功，返回true
            System.out.println("过此");
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }


    //在登录的情况下会走此方法，此方法返回true直接访问控制器，这里客户端的请求会走上面的方法，返回true则代表已登录可以直接访问控制器
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //if(isLoginAttempt(request,response)) {
            //System.out.println(isLoginAttempt(request,response));
            try {
                return executeLogin(request, response);
                // return true;有一篇博客这里直接返回true是不正确的,在这里我特别指出一下
            } catch (Exception e) {
                log.error("JwtFilter过滤验证失败!");
                return false;
            }
        //}
    }

/*    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断是否是登录请求
        String authorization = req.getHeader("token");
        return authorization != null;
    }*/

/*    public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory{

        @Override
        public Subject createSubject(SubjectContext context){
            context.setSessionCreationEnabled(false);
            return super.createSubject(context);
        }

    }*/


}
