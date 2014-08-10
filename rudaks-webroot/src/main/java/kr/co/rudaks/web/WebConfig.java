package kr.co.rudaks.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.rudaks.web.common.app.ConfigFactory;

public class WebConfig 
{	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(WebConfig.class);
	
	private static ConfigFactory configFactory = null;
	
	public static String getString(String key, String defaultValue)
    {
		String result = null;
		try
		{
			configFactory = ConfigFactory.getInstance();
			result = configFactory.getPropertyValue(key, defaultValue);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
        return result;
    }
	
	public static String getString(String key)
    {
		return getString(key, null);
    }
}
