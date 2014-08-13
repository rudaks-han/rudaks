package kr.co.rudaks.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.bean.LoginForm;
import kr.co.rudaks.web.service.IAccessLogService;
import kr.co.rudaks.web.util.CookieUtil;
import kr.co.rudaks.web.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

public class HandlerInterceptor extends HandlerInterceptorAdapter
{
	private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IAccessLogService accessLogService;
	
    public HandlerInterceptor()
    {        
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (request.getSession(false) == null)
        {
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
            Browser browser = userAgent.getBrowser();
            OperatingSystem os = userAgent.getOperatingSystem();
            if (browser != null && os != null)
            {
                DBObject info = new BasicDBObject();
                info.put("session_id", request.getSession(true).getId());
                info.put("created_date", DateUtil.getCurrDate("yyyyMMddHHmmss"));
                info.put("ip", request.getRemoteAddr());
                info.put("referer", request.getHeader("referer"));
                info.put("browser", browser.getName());
                info.put("os", os.getName());
                accessLogService.createAccessLog(info.toString());
            }
        }
                
        String adminId = null;
        try
        {
            adminId = CookieUtil.getCookie(request, true, WebPublic.COOKIE_UID);
            
            if (WebPublic.ADMIN_ID.equals(adminId))
            {
                LoginForm loginForm = new LoginForm();
                loginForm.setUserId(WebPublic.ADMIN_ID);
                loginForm.setUsername(WebPublic.ADMIN_NAME);
                loginForm.setEmail(WebPublic.ADMIN_EMAIL);
                request.getSession().setAttribute(WebPublic.SESSION_INFO, loginForm);
            }
            else
            {
                request.getSession().removeAttribute(WebPublic.SESSION_INFO);
            }
            
            
            String sessionId = request.getSession().getId();
            
            //String accessLog = accessLogService.selectAccessLog(sessionId);
                
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage());
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
