package kr.co.rudaks.web;

import kr.co.rudaks.web.common.app.ConfigFactory;

public class WebConfig 
{	
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
			e.printStackTrace();
		}
        return result;
    }
	
	public static String getString(String key)
    {
		return getString(key, null);
    }
}
