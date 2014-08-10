package kr.co.rudaks.web.bean;

import java.util.List;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.util.DateUtil;
import lombok.Data;

@Data
public class PostForm
{
    private int id;
    private String username;
    private String email;
    private String title;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String content;
    private String category;
    private String categoryName;
    private int viewCount;
    private int attachCount;
    private String ipaddress;
    private String deleteFlag;
    private String createdDate;
    private String createdDateDisplay;
    private String updatedDate;
    private String updatedDateDisplay;
    private List<AttachFileForm> attachFileList;
    private int oldSeq;
    
    private int page;
    private int rowsPerPage;
    private int startNo;
    private int endNo;
     
    public String getCreatedDateDisplay()
    {
        String createdDateDisplay = createdDate;
        
        try
        {
	        if (createdDate != null)
	        	createdDateDisplay = DateUtil.formatDateString(createdDate, WebPublic.DATE_FORMAT_YYYYMMDDHHMMSS, WebPublic.DATE_FORMAT_DISPLAY);
	        else
	        	createdDateDisplay = createdDate;
        }
        catch (Exception e) {}
        return createdDateDisplay;
    }
}
