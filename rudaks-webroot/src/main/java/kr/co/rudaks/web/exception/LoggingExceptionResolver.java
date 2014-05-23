package kr.co.rudaks.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


import ch.qos.logback.classic.Logger;

public class LoggingExceptionResolver extends SimpleMappingExceptionResolver 
{
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    protected void logException(Exception ex, HttpServletRequest request) 
    {
        // ERROR 로그 남기는 부분
        // resolveException 메소드가 있으면 resolveException 메소드가 처리된다.
        ex.printStackTrace();
        this.logger.warn(buildLogMessage(ex, request), ex);
    }
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) 
    {
        // 로그 발생시 호출되는 부분
        request.setAttribute("exception",e);  // request로 exception을 넘긴다.        
        e.printStackTrace();
        //return new ModelAndView(WebPublic.ServicePath.ERROR + "." + WebPublic.ServicePath.ERROR_500);
        return null;
    }
    
}
