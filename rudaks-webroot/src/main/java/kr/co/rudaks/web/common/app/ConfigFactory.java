package kr.co.rudaks.web.common.app;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigFactory
{
    private static Logger logger = (Logger) LoggerFactory.getLogger(ConfigFactory.class);
    private static ConfigFactory instance = null;
    
    private static Properties serverProperty = new Properties();
    
    public static synchronized ConfigFactory getInstance() throws Exception
    {
        if(instance == null)
        {
        	instance = new ConfigFactory();
            load();
        }
        return instance;
    }
    
    @SuppressWarnings("rawtypes")
    private static void load() throws Exception
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        serverProperty.load( loader.getResourceAsStream("rudaks.properties"));
        Iterator<Entry<Object,Object>> iterator = serverProperty.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry) iterator.next();
            logger.info("{} : {}",entry.getKey(),entry.getValue());
        }
    }

    public Properties getServerProperties()
    {
        return serverProperty;
    }
    
    public String getPropertyValue(String key)
    {
        return serverProperty.getProperty(key);
    }
    
    public String getPropertyValue(String key,String defaultValue)
    {
        String value = serverProperty.getProperty(key);
        if(value == null)
        {
            value = defaultValue;
        }
        return value;
    }

    
}
