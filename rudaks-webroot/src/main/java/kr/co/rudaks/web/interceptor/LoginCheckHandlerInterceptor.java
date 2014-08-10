package kr.co.rudaks.web.interceptor;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.service.IPostService;
import kr.co.rudaks.web.util.CookieUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckHandlerInterceptor extends HandlerInterceptorAdapter
{
	private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
    public LoginCheckHandlerInterceptor()
    {        
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String userId = CookieUtil.getCookie(request, true, WebPublic.COOKIE_UID);
        
        if (!WebPublic.ADMIN_ID.equals(userId))
        {
            response.sendRedirect("/");
            
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
    }   
}
