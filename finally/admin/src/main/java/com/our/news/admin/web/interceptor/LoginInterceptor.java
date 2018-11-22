package com.our.news.admin.web.interceptor;

import com.ournews.commons.constant.ConstantUtils;
import com.ournews.domain.entity.AdminUser;
import com.ournews.domain.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        AdminUser adminUser = (AdminUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_ADMIN);
        //用户信息为空时，返回登录界面
        if (adminUser==null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
        }
        //放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
