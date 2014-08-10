package kr.co.rudaks.web.bean;

import lombok.Data;

@Data
public class AttachFileForm
{	
    private int postId;
    private int seq;
	private boolean success;
	private String filePath;
	private String fileName;
	private long fileSize;
	private String msg;
}
