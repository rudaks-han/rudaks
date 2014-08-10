package kr.co.rudaks.web.bean;

import lombok.Data;

@Data
public class NotifyMessage implements java.io.Serializable
{
	private String elementPosition;
	private String className;
	private String message;
	private String element;
	
	public NotifyMessage(String message, String className)
	{
	    this.message = message;
	    this.className = className;
	}
	
	public NotifyMessage(String message, String className, String element)
    {
        this.message = message;
        this.className = className;
        this.element = element;
    }
}
