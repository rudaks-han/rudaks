package kr.co.rudaks.web.bean;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessLogForm 
{
	@Id
	private int id;
	private String date;
	private String time;
	private String ipaddress;
	private String referer;
	private String browser;
	private String os;
}