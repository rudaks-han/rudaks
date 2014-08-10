package kr.co.rudaks.web.service;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Service
public class AccessLogServiceImpl implements IAccessLogService
{
    private static final String ACCESS_LOG_COLLECTION = "access_log";
    private static final String ACCESS_LOG_COLLECTION_PK = "session_id";
    
    private static final String DOWNLOAD_LOG_COLLECTION = "download_log";
    
    @Resource
    public MongoTemplate mongoTemplate;
    
    @Override
    public void createAccessLog(String info) 
    {
        mongoTemplate.save(JSON.parse(info), ACCESS_LOG_COLLECTION);
    }
    
    @Override
    public void createDownloadLog(String info) 
    {
        mongoTemplate.save(JSON.parse(info), DOWNLOAD_LOG_COLLECTION);
    }
    
    @Override
    public String selectAccessLog(String sessionId) 
    {
        DBObject info = mongoTemplate.findOne(new BasicQuery(new BasicDBObject(ACCESS_LOG_COLLECTION, sessionId)), BasicDBObject.class, ACCESS_LOG_COLLECTION_PK);
        if (info == null)
            return null;
        return info.toString();
    }
    
}
