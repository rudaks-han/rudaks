package kr.co.rudaks.web.util;

import org.apache.commons.lang.StringUtils;

/**
 * String(문자열) 조작 및 관련 데이터의 컨버젼 기능을 가진다.
 *
 */
public class StringUtil extends StringUtils
{	

    /**
    * 대소문자 구분없이 주어진 문자열에서 old String에 해당하는 문자를 new String값으로 대체해서 리턴한다.
    *
    * @param string 변환하고자하는 문자열
    * @param oldString 대체하고자 하는 문자열
    * @param newString 대체할 하는 문자열
    *
    * @return String 변환된 문자열
    */
    public static String replaceAll(String string, String oldString, String newString)
    {
    	try
    	{
	        if(string == null || oldString == null || newString == null )
	        {
	            return null;
	        }
	        
	        if (isEmpty(oldString)) {
	            return string;
	        }

	        String str = string;
	        String uppperOldString = oldString.toUpperCase();
	        String upperStr = str.toUpperCase();
	        String realStr = "";
	        int idx = -1;
	        int fromIdx = 0;
	
	        while( (idx = upperStr.indexOf(uppperOldString , fromIdx)) != -1 ) // NOPMD by yshwang on 13. 11. 11 오후 3:33
	        {
	            realStr = str.substring(idx, idx+oldString.length());
	            realStr = realStr.toUpperCase();
	            str = str.substring(0, idx) + realStr + str.substring(idx+oldString.length(), str.length());
	            fromIdx = idx+oldString.length();
	        }
	
	        return replace(str, uppperOldString, newString);
    	}
    	catch(Exception e)
        {
    		//e.printStackTrace();
    		return string;
        }        
    }


}