package kr.co.rudaks.web.service;

public interface IAccessLogService
{
    void createAccessLog(String info);
    
    void createDownloadLog(String info);
    
    String selectAccessLog(String sessionId);
}
