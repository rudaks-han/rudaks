package kr.co.rudaks.web.service;

import javax.annotation.Resource;

import kr.co.rudaks.web.controller.MainController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Service
public class AccessLogServiceImpl implements IAccessLogService
{
	private final static Logger logger = (Logger) LoggerFactory.getLogger(AccessLogServiceImpl.class);
	
    private static final String ACCESS_LOG_COLLECTION = "access_log";
    private static final String ACCESS_LOG_COLLECTION_PK = "session_id";
    
    private static final String DOWNLOAD_LOG_COLLECTION = "download_log";
    
    @Resource
    public MongoTemplate mongoTemplate;
    
    @Override
    public void createAccessLog(String info) 
    {
    	try
    	{
    		mongoTemplate.save(JSON.parse(info), ACCESS_LOG_COLLECTION);
    	}
    	catch (Exception e)
    	{
    		logger.error(e.getMessage(), e);
    	}
    }
    
    @Override
    public void createDownloadLog(String info) 
    {
    	try
    	{
    		mongoTemplate.save(JSON.parse(info), DOWNLOAD_LOG_COLLECTION);
    	}
    	catch (Exception e)
    	{
    		logger.error(e.getMessage(), e);
    	}
    }
    
    @Override
    public String selectAccessLog(String sessionId) 
    {
        DBObject info = null;
        try
    	{
        	info = mongoTemplate.findOne(new BasicQuery(new BasicDBObject(ACCESS_LOG_COLLECTION, sessionId)), BasicDBObject.class, ACCESS_LOG_COLLECTION_PK);
	    }
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
        
        if (info == null)
            return null;
        return info.toString();
    }
    
}
