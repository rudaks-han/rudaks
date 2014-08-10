package kr.co.rudaks.web.util;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class Util 
{
	 /**
     * 주어진 String 변수를 Null 값을 체크해서 int 형 또는 default int value로 리턴한다.
     *
     * @param str String 변수
     * @param i Default Value
     * @return int 로 변환된값.
     */
    public static int str2i(String str, int i)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch(Exception e)
        {
        }

        return i;
    }
    
    public static boolean str2bl(String str, boolean b)
    {
        try
        {
            return (new Boolean(str)).booleanValue();
        }
        catch(Exception e)
        {
        }

        return b;
    }
    
    /**
     * 주어진 String 변수를 Null 값을 체크해서 int 형을 리턴한다.<br>
     * Null 일경우 0을 리턴한다.
     *
     * @param str String 변수
     * @return int 로 변환된값.
     * @throws RunUtilException the run util exception
     */
    public static int str2i(String str) throws Exception
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch(Exception e)
        {
            throw e;
        }

        // return 0;
    }
    
    public static long str2l(String str, long l)
    {
        try
        {
            return Long.parseLong(str);
        }
        catch(Exception e)
        {
        }

        return l;
    }

    public static long str2l(String str) throws Exception
    {
        try
        {
            return Long.parseLong(str);
        }
        catch(Exception e)
        {
            throw new Exception("Util.str2l - Invaild long String : " + str, e);
        }

        // return 0L;
    }
    
    public static String getGuid()
    {
        StringBuffer szGuid = new StringBuffer();
        String szUid = new java.rmi.server.UID().toString();
        Date date1 = new Date();

        long lTime = date1.getTime();
        szGuid.append(szUid).append(lTime);

        String szResult = szGuid.toString();
        if(szResult.length() < 36)
        {
            for(int i = szResult.length(); i < 37; i++)
                szResult += "0";
        }
        String szOutResult = szResult.substring(0, 36);

        return szOutResult;
    }
    
    public static String getEmailKey(String email)
    {
    	Random rand = new Random(System.currentTimeMillis()); 
    	return email + "," + rand.nextInt();
    }
    
	/**
	 * 아이피가 특정 범위에 포함되어 있는지 체크하는 함수
	 * range : 192.168.*.*
	 * range : 192.168.[0-100].*
	 */
	public static final boolean isIpRange(String range, String ip)
	{
		try
		{
			String [] arRange = StringUtils.split(range, ".");
			String [] arIp = StringUtils.split(ip, ".");
			if (arRange.length == 4 && arIp.length == 4)
			{
				int validSize = 0; // 크기가 4개가 되면 모두 체크된 상태
				for (int i=0; i<arIp.length; i++)
				{
					if ("*".equals(arRange[i]))
					{
						validSize++;
						continue;
					}
					else if (arRange[i].startsWith("[") && arRange[i].endsWith("]"))
					{
						String temp = arRange[i].substring(1, arRange[i].length()-1); // 0-9
						String [] arTemp = StringUtils.split(temp, "-"); // 
						if (arTemp.length == 2)
						{
							if (Util.str2i(arIp[i]) >= Util.str2i(arTemp[0]) && Util.str2i(arIp[i]) <= Util.str2i(arTemp[1]))
							{
								validSize++;
								continue;
							}
						}
						else
							return false;
					}
					else
					{
						if (Util.str2i(arRange[i]) == Util.str2i(arIp[i]))
						{
							validSize++;
							continue;
						}
					}
				}
				
				if (validSize == 4)
					return true;
			}
		}
		catch (Exception e)
		{
			System.out.println("invalid ip range==> range : " + range + ", ip : " + ip);
			e.printStackTrace();
		}
		return false;
	}

	
	public static String asc2utf(String str)
	{
		try
		{			
			if (str != null && str.length() > 0)
				str = new String(str.getBytes("8859_1"), "UTF-8");				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getRandomIp()
	{
		return Math.round(Math.random()*254) + "." + Math.round(Math.random()*254) + "." + Math.round(Math.random()*254) + "." + Math.round(Math.random()*254);
	}
	
    public static void main(String [] args)
    {
    	/*String str = Util.getEmailKey("rudaks94@daum.net");
    	System.out.println(str);*/
    	
    	boolean flag = Util.isIpRange("192.[168-169].*.*", "192.168.0.0");
    	System.out.println(flag);
    }
}
