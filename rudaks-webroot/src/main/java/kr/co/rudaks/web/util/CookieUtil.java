package kr.co.rudaks.web.util;

import java.util.Vector;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import kr.co.rudaks.web.WebPublic;

/**
 * 쿠키 관련 클래스.
 *
 * @author kmhan
 */
public class CookieUtil
{
    /** 쿠키유지설정. */
    private static int maxAge = 60*60*24*1000;

	/**
	 * 쿠키값을 세팅한다.
	 *
	 * @param response HttpServletResponse 객체.
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param iMaxAge 유지시간.
	 *
	 * @throws Exception the exception
	 */
    public static void setCookie(HttpServletResponse response, String name, String value, int iMaxAge)
            throws Exception
    {
    	String cookieValue = null;
    	cookieValue = (value == null) ? "" : java.net.URLEncoder.encode(value,"UTF-8");
        Cookie cookie = new Cookie(name, cookieValue);
        cookie.setMaxAge(iMaxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
	 * 쿠키값을 세팅한다.
     *
     * @param response HttpServletResponse 객체.
	 * @param name 쿠키명.
	 * @param value 쿠키값.
     *
     * @throws Exception the exception
     */
    public static void setCookie(HttpServletResponse response, String name, String value)
    throws Exception
    {
        setCookie(response, name, value, maxAge);
    }

	/**
	 * <pre>
	 * 쿠키값을 세팅한다.
	 * -uri 정보 포함
	 * </pre>
	 *
	 * @param response HttpServletResponse 객체.
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
	 * @param iMaxAge 유지시간.
	 *
	 * @throws Exception the exception
	 */
    public static void setCookie(HttpServletResponse response, String name, String value,String uri, int iMaxAge)
            throws Exception
    {
    	String cookieValue = null;
    	cookieValue = (value == null) ? "" : java.net.URLEncoder.encode(value,"UTF-8");
        Cookie cookie = new Cookie(name, cookieValue);
        cookie.setPath(uri);
        cookie.setMaxAge(iMaxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
	 * <pre>
	 * 쿠키값을 세팅한다.
	 * -uri 정보 포함
	 * </pre>
     *
     * @param response HttpServletResponse 객체.
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
     *
     * @throws Exception the exception
     */
    public static void setCookie(HttpServletResponse response, String name, String value,String uri)
        throws Exception
    {
        setCookie(response, name, value, uri, maxAge);
    }

	/**
	 * <pre>
	 * 쿠키값을 세팅한다.
	 * -uri 정보 포함, 암호화
	 * </pre>.
	 *
	 * @param response HttpServletResponse 객체.
	 * @param bEncrypt 암호화(true[암호화를 시행함] or false[암호화를 하지 않음]).
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
	 * @param iMaxAge 유지시간.
	 *
	 * @throws Exception the exception
	 */
    public static void setCookie(HttpServletResponse response, boolean bEncrypt, String name, String value,String uri, int iMaxAge)
            throws Exception 
    {
    	setCookie(response, bEncrypt, name, value,uri, iMaxAge, false);
    }

    public static void setCookie(HttpServletResponse response, boolean bEncrypt, String name, String value,String uri, int iMaxAge, boolean bSecure)
            throws Exception
    {
    	 //value = java.net.URLEncoder.encode(value,"UTF-8");
        Cookie cookie = null;
        String cookieValue = null;
        if (bEncrypt)
        {	
        	cookieValue = CryptoUtil.encrypt(WebPublic.SHA_SALT_KEY, value);
            cookie = new Cookie(name, cookieValue);
        }
        else
            cookie = new Cookie(name, value);
        cookie.setPath(uri);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(iMaxAge);
        cookie.setSecure(bSecure);
        response.addCookie(cookie);
    }
    
    /**
	 * 쿠키값을 세팅한다.
     *
     * @param response HttpServletResponse 객체.
	 * @param bEncrypt 암호화(true[암호화를 시행함] or false[암호화를 하지 않음]).
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
     *
     * @throws Exception the exception
     */
    public static void setCookie(HttpServletResponse response, boolean bEncrypt, String name, String value, String uri)
        throws Exception
    {
        setCookie(response, bEncrypt,  name, value, uri, maxAge);
    }
    
    public static void setCookie(HttpServletResponse response, boolean bEncrypt, String name, String value, int maxAge)
            throws Exception
        {
            setCookie(response, bEncrypt,  name, value, "/", maxAge);
        }
    
    public static void setCookie(HttpServletResponse response, boolean bEncrypt, String name, String value)
            throws Exception
    {
            setCookie(response, bEncrypt,  name, value, "/", maxAge);
    }

	/**
	 * <pre>
	 * 쿠키값을 세팅한다.
	 * -uri 정보 포함, 암호화하고 URLEncoding해서 쿠키에 넣는다.
	 * </pre>
	 *
	 * @param response HttpServletResponse 객체.
	 * @param bEncrypt 암호화(true[암호화를 시행함] or false[암호화를 하지 않음]).
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
	 * @param encoding 인코딩.
	 * @param iMaxAge 유지시간.
	 *
	 * @throws Exception the exception
	 */
    public static void setCookieByEncoding(javax.servlet.http.HttpServletResponse response, boolean bEncrypt, String name, String value,String uri, String encoding, int iMaxAge)
            throws Exception
    {
        String ecryptValue = null;
        Cookie cookie = null;
        if (bEncrypt)
        {
        	ecryptValue = CryptoUtil.encrypt(WebPublic.SHA_SALT_KEY, value);            
            cookie = new Cookie(name, java.net.URLEncoder.encode(ecryptValue, encoding));
        }
        else
        {
            cookie = new Cookie(name, java.net.URLEncoder.encode(value, encoding));
        }
        cookie.setPath(uri);
        cookie.setMaxAge(iMaxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
	 * 쿠키값을 세팅한다.
	 * uri 정보 포함, 암호화하고 URLEncoding해서 쿠키에 넣는다.
     *
     * @param response HttpServletResponse 객체.
	 * @param bEncrypt 암호화(true[암호화를 시행함] or false[암호화를 하지 않음]).
	 * @param name 쿠키명.
	 * @param value 쿠키값.
	 * @param uri 관련uri.
	 * @param encoding 인코딩.
	 *
     * @throws Exception the exception
     */
    public static void setCookieByEncoding(HttpServletResponse response, boolean bEncrypt, String name, String value,String uri, String encoding)
        throws Exception
    {
        setCookieByEncoding(response, bEncrypt, name, value, uri, encoding, maxAge);
    }

	/**
	 * 생성된 쿠키값을 가져온다.
	 *
	 * @param request HttpServletRequest 객체.
	 * @param cookieName 쿠키명.
	 *
	 * @return 쿠키값.
	 *
	 * @throws Exception the exception
	 */
    public static String getCookie(HttpServletRequest request, String cookieName)
            throws Exception
    {
        Cookie [] cookies = request.getCookies();
        String value = null;
        if( cookies != null )
        {
            for(int i=0;i<cookies.length;i++)
            {
                if(cookieName.equals(cookies[i].getName()))
                {
                    value = java.net.URLDecoder.decode(cookies[i].getValue(),"UTF-8");
                    break;
                }
            }
        }
        return value;
    }

    /**
	 * 생성된 쿠키값을 가져온다.
     *
     * @param request HttpServletRequest 객체.
     * @param cookieName 쿠키명.
     * @param itemName 키.
     *
     * @return 쿠키값.
     *
     * @throws Exception the exception
     */
    public static String getCookieByList(HttpServletRequest request, String cookieName, String itemName) throws Exception
    {
    	return getCookieByList(request, cookieName, itemName, ",");
    }

    /**
	 * 생성된 쿠키값을 가져온다.
     *
     * @param request HttpServletRequest 객체.
     * @param cookieName 쿠키명.
     * @param itemName 키.
     * @param separator 구분자.
     *
     * @return 쿠키값.
     *
     * @throws Exception the exception
     */
    public static String getCookieByList(HttpServletRequest request, String cookieName, String itemName, String separator)
    throws Exception
	{
		Cookie [] cookies = request.getCookies();
		String value = null;
		if( cookies != null )
		{
		    for(int i=0;i<cookies.length;i++)
		    {
		        if(cookieName.equals(cookies[i].getName()))
		        {
		            String list = java.net.URLDecoder.decode(cookies[i].getValue(),"UTF-8");
		            String [] arList = list.split(separator);
		            for (int j=0; j<arList.length; j++)
		            {
		            	String [] arItem = arList[j].split("=");
		            	if (arItem != null && arItem.length == 2 && arItem[0].equals(itemName))
		            	{
		            		value = arItem[1];
		            		break;
		            	}
		            }
		            break;
		        }
		    }
		}
		return value;
	}

	/**
	 * <pre>
	 * 쿠키값 가져오기
	 * 복호화
	 * </pre>.
	 *
	 * @param request HttpServletRequest 객체.
	 * @param bDecrypt 복호화(true[복화화 시행] or false[복호화를 시행하지 않음].
	 * @param cookieName 쿠키명.
	 *
	 * @return 쿠키값.
	 *
	 * @throws Exception the exception
	 */
    public static String getCookie(HttpServletRequest request, boolean bDecrypt, String cookieName)
            throws Exception
    {
        return getCookie(request, bDecrypt, cookieName, null);
    }

	/**
	 * <pre>
	 * 쿠키값 가져오기
	 * 복호화, URLDecode해서 가져온다.
	 * </pre>
	 *
	 * @param request HttpServletRequest 객체.
	 * @param bDecrypt 복호화(true[복화화 시행] or false[복호화를 시행하지 않음].
	 * @param cookieName 쿠키명.
	 * @param encoding 인코딩.
	 *
	 * @return 쿠키값.
	 *
	 * @throws Exception the exception
	 */
    public static String getCookie(HttpServletRequest request, boolean bDecrypt, String cookieName, String encoding)
            throws Exception
    {
        Cookie [] cookies = request.getCookies();
        String value = null;
        if( cookies != null )
        {
            for(int i=0;i<cookies.length;i++)
            {
                if(cookieName.equals(cookies[i].getName()))
                {
                    value = cookies[i].getValue();
                    if (value == null)
                        return null;
                    if (encoding != null && encoding.length() > 0)
                        value = java.net.URLDecoder.decode(value, encoding);
                    else
                        value = java.net.URLDecoder.decode(value);

                    if (bDecrypt)
                    {                    	
                    	value = CryptoUtil.decrypt(WebPublic.SHA_SALT_KEY, value);      
                        //value = Decrypt.decryptString(value, encryptKey);
                    }
                    break;
                }
            }
        }
        return value;
    }
    
    public static Vector getCookies(HttpServletRequest request) throws Exception
	{
    	Vector vResult = new Vector();
    	Cookie [] cookies = request.getCookies();
    	String value = null;
    	if( cookies != null )
    	{
    		String [] arCookie = null; 
    		for(int i=0;i<cookies.length;i++)
    		{
    			arCookie = new String[2];
    			arCookie[0] = cookies[i].getName();
    			arCookie[1] = cookies[i].getValue() == null ? "" : cookies[i].getValue();
    			vResult.addElement(arCookie);    			
    		}
    	}
    	return vResult;
	}
    
    public static void main(String [] args) throws Exception
    {
    	String value = CryptoUtil.encrypt(WebPublic.SHA_SALT_KEY, "31");
    	System.err.println(value);
    }

}