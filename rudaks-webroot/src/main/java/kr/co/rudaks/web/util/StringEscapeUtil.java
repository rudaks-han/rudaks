package kr.co.rudaks.web.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * String(문자열) 조작에서 자바스크립, SQL, HTML등의 escape 또는 unescape 기능 가진 클래스.
 *
 * @author kspark
 */
public class StringEscapeUtil extends StringEscapeUtils
{
    /**
     * XSS 공격 방지에 대한 문자 변환을 한다.
     * @param str - source string.
     * @return - XSS 공격 대상 문자가 변경된 string.
     * <pre>
     *      아래 문자에 대해서 문자열 치환이 일어난다.
     *      한국정보화진흥원 : & < > " ' / ( ) 
     *      스펙트라 : & < > " ' / ( ) % --
     *      text = text.replace("&",  "&amp;");
     *      text = text.replace("<",  "&lt;");
     *      text = text.replace(">",  "&gt;");
     *      text = text.replace("\"", "&quot;");    
     *      text = text.replace("'",  "&#39;");
     *      text = text.replace("/",  "&#47;");
     *      text = text.replace("(",  "&#40;");
     *      text = text.replace(")",  "&#41;");
     *      text = text.replace("%",  "&#37;");
     *      text = text.replace("--", "&#45;&#45;");
     * </pre>
     */
    public static String escapeXSS(String plainText) 
    {
        String targetText = plainText;
        if (targetText != null) 
        {
            /*String[] source = {"&", "<", ">", "\"", "'", "/", "(", ")", "%", "-"};
            String[] target = {"&amp;","&lt;","&gt;","&quot;","&#39;","&#47;","&#40;","&#41;","&#37;","&#45;"};*/
            String[] source = {"&", "<", ">", "\"", "'", "/", "(", ")", "%", "-"};
            String[] target = {"&amp;","&lt;","&gt;","&quot;","&#39;","&#47;","&#40;","&#41;","&#37;","&#45;"};
            
            for(int i = 0; i < source.length; i++)
            {
                targetText = targetText.replace(source[i], target[i]);
            }
        }
        return targetText;
    }
    
    /**
     * XSS 공격 방지를 위해 변환된 문자를 원본 문자로 복원한다.
     * @param str - source string.
     * @return - XSS 공격 대상 문자가 복원된 string.
     * <pre>
     *      아래 문자에 대해서 문자열 치환이 일어난다.
     *      한국정보화진흥원 : & < > " ' / ( ) 
     *      스펙트라 : & < > " ' / ( ) % --
     *      text = text.replace("&amp;", "&");
     *      text = text.replace("&lt;", "<");
     *      text = text.replace("&gt;", ">");
     *      text = text.replace("&quot;", "\"");    
     *      text = text.replace("&#39;", "'");
     *      text = text.replace("&#47;", "/");
     *      text = text.replace("&#40;", "(");
     *      text = text.replace("&#41;", ")");
     *      text = text.replace("&#37;", "%");
     *      text = text.replace("&#45;&#45;", "--");
     * </pre>
     */
    public static String unescapeXSS(String encText) 
    {
        String plainText = encText;
        if (plainText != null)
        {
            String[] source = {"&amp;","&lt;","&gt;","&quot;","&#39;","&#47;","&#40;","&#41;","&#37;","&#45;"};
            String[] target = {"&", "<", ">", "\"", "'", "/", "(", ")", "%", "-"};
            
            for(int i = 0; i < source.length; i++)
            {
                if(plainText.indexOf(source[i]) > -1)
                {
                    plainText = plainText.replace(source[i], target[i]);
                }
            }
        }
        return plainText;
    }
}