package kr.co.rudaks.web.bean;

import java.util.List;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.util.DateUtil;
import lombok.Data;

@Data
public class GuestbookForm
{
    private int id;
    private int ref;
    private String username;
    private String email;
    private String password;
    private String comment;
    private String ipaddress;
    private String deleteFlag;
    private String createdDate;
    private String createdDateDisplay;
    private String updatedDate;
    private String updatedDateDisplay;
    
    
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return createdDateDisplay;
    }
}
