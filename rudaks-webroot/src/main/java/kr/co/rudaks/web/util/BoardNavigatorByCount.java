package kr.co.rudaks.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardNavigatorByCount
{
	private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    /** 페이지 전체개수. */
    private int totalCount = -1;

    /** 현재페이지. */
    private int currentPage;

    /** 시작번호. */
    private int startRecNo;

    /** 끝번호. */
    private int endRecNo;

    /** 한페이지 나오는 글개수. */
    private int rowsPerPage = 10;

    /** 페이지번호에 나오는 개수(1-2-3-4-5-6-7-8-9-10). */
    private int unitCount = 10;

    /** 개수 가져오는 query. */
    private String countQuery = "";

    /** 처음으로. */
    private String toFirst = "◁◁";

    /** 끝으로. */
    private String toEnd = "▷▷";

    /** 이전페이지. */
    private String previous = "◁";

    /** 다음페이지. */
    private String forward = "▷";
    
    /** 처음으로 css name */
    private String toFirstClassName = "";
    
    /** 끝으로 css name */
    private String toEndClassName = "";
    
    /** 이전페이지 css name */
    private String previousClassName = "";
    
    /** 다음페이지 css name */
    private String forwardClassName = "";
    
    /** 현재페이지 css name */
    private String currentPageClassName = "";

    /** toFirst와 toEnd를 보여줄것인지. */
    private boolean terminalDisplay = false;

    /** 이전,다음페이지를 보여줄것인지. */
    private boolean nextAndPrevDisplay = true;

    /** 전체 갯수. */
    private int totalUnits;

    /**
     * 생성자.
     *
     * @param totalCount 전체갯수.
     * @param rowsPerPage 한페이지 나오는 글 개수.
     * @param currentPage 현재페이지.
     */
    public BoardNavigatorByCount(int totalCount, int rowsPerPage, int currentPage)
    {
        this.totalCount = totalCount;
        this.rowsPerPage = rowsPerPage;
        this.currentPage = currentPage;
        setTotalCount(totalCount);
    }

    /**
     * 이미지 tag설정. - 처음으로.
     *
     * @param str 처음 이미지 tag.
     */
    public void setToFirstSymbol(String str)
    {
        toFirst = str;
    }

    /**
     * 이미지 tag설정. - 끝으로.
     *
     * @param str 끝 이미지 tag.
     */
    public void setToEndSymbol(String str)
    {
        toEnd = str;
    }

    /**
     * 이미지 tag설정. - 다음페이지(블럭).
     *
     * @param str 다음 이미지 tag.
     */
    public void setForwardSymbol(String str)
    {
        forward = str;
    }

    /**
     * 이미지 tag설정. - 이전페이지(블럭).
     *
     * @param str 이전 이미지 tag.
     */
    public void setPreviousSymbol(String str)
    {
        previous = str;
    }

    /**
     * 이미지 tag설정(img src만 설정). - 처음으로.
     *
     * @param img 처음으로 img 이미지 src.
     */
    public void setImgToFirst(String img)
    {
        setToFirstSymbol("<img src=" + img + " border=\"0\">");
    }

    /**
     * 이미지 imgtag설정(img src만 설정). - 끝으로.
     *
     * @param img 끝으로 img 이미지 src.
     */
    public void setImgToEnd(String img)
    {
        setToEndSymbol("<img src=" + img + " border=\"0\">");
    }

    /**
     * 이미지 tag설정(img src만 설정). - 다음페이지(블럭).
     *
     * @param img 다음페이지 이미지 src.
     */
    public void setImgForward(String img)
    {
        setForwardSymbol("<img src=" + img + " border=\"0\">");
    }

    /**
     * 이미지 tag설정(img src만 설정). - 이전페이지(블럭).
     *
     * @param img 이전페이지 이미지 src.
     */
    public void setImgPrevious(String img)
    {
        setPreviousSymbol("<img src=" + img + " border=\"0\">");
    }

    /**
     * toFirst와 toEnd를 보여줄것인지 설정.
     *
     * @param terminalDisplay true(첫페이지/끝페이지 이미지를 나타냄) or false(첫페이지/끝페이지 이미지를 나태내지 않음).
     */
    public void setTerminalDisplay(boolean terminalDisplay)
    {
        this.terminalDisplay = terminalDisplay;
    }

    /**
     * toFirst와 toEnd를 보여줄것인지 설정값을 가져온다.
     *
     * @return true(보여줌) or false(숨김).
     */
    public boolean getTerminalDisplay()
    {
        return this.terminalDisplay;
    }

    /**
     * 이전,다음페이지를 보여줄것인지를 설정.
     *
     * @param nextAndPrevDisplay true(보여줌) or false(숨김).
     */
    public void setNextAndPrevDisplay(boolean nextAndPrevDisplay)
    {
        this.nextAndPrevDisplay = nextAndPrevDisplay;
    }

    /**
     * 이전,다음페이지를 보여줄것인지를 설정값을 가져온다.
     *
     * @return nextAndPrevDisplay true(보여줌) or false(숨김).
     */
    public boolean getNextAndPrevDisplay()
    {
        return this.nextAndPrevDisplay;
    }

    /**
     * 전체 갯수를 설정한다.
     *
     * @param cnt 전체갯수.
     */
    public void setTotalCount(int cnt)
    {
        totalCount = cnt;
        if (rowsPerPage == 0)
        {
        	System.err.println("rowsPerPage is zero.");
        	return;
        }
        	
        totalUnits = totalCount / rowsPerPage;
        totalUnits = (totalCount % rowsPerPage) == 0 ? totalUnits : totalUnits + 1;
    }

    /**
     * 한페이지 나오는 글개수를 설정한다.
     *
     * @param rp 갯수.
     */
    public void setRowsPerPage(int rp)
    {
        rowsPerPage = rp;
    }

    /**
     * 페이지번호에 나오는 개수(1-2-3-4-5-6-7-8-9-10)를 설정한다.
     *
     * @param unitCount 갯수.
     */
    public void setUnitCount(int unitCount)
    {
        this.unitCount = unitCount;
    }

    /**
     * 페이지번호에 나오는 개수를 가져온다.
     *
     * @return unitCount 갯수.
     */
    public int getUnitCount()
    {
        return this.unitCount;
    }

    /**
     * 전체갯수를 가져온다.
     *
     * @return 전체갯수.
     */
    public int getTotalCount()
    {
        return totalCount;
    }
    
    public int getTotalUnit()
    {
    	return totalUnits;
    }

    /**
     * 카우트쿼리를 가져온다.
     *
     * @return 카운트쿼리.
     */
    public String getCountQuery()
    {
        return this.countQuery;
    }

    /**
     * 카운트쿼리를 대입한다.
     *
     * @param query 카운트쿼리
     */
    public void setCountQuery(String query)
    {
        this.countQuery = query;
    }

    /**
     * 생성된 페이징 html을 가져온다.
     *
     * @return linkHtml 페이징 html.
     */
    public String getLinkHtml()
    {
        StringBuffer sb = new StringBuffer();
        if(currentPage < 0)
        {
            currentPage = 1;
        }
        else if(currentPage >= totalUnits)
        {
            currentPage = totalUnits;
        }

        startRecNo = (currentPage - 1) * rowsPerPage + 1;
        endRecNo = startRecNo + rowsPerPage - 1;
        endRecNo = (endRecNo >= totalCount) ? totalCount : endRecNo;

        int backPageNo = (currentPage - 1) / unitCount;// to determine back link to be displayed or not
        int startPageNo = backPageNo * unitCount + 1;
        int pageNo = startPageNo;
        if(backPageNo >= 1)
        {
            if(terminalDisplay)
                sb.append("<a href=\"JavaScript:goto(1);\">" + toFirst + "</a>&nbsp;&nbsp;");
            sb.append("<a href=\"JavaScript:goto(" + (startPageNo - unitCount) + ")\">" + previous + "</a>&nbsp;");
        }
        else if(nextAndPrevDisplay)
        {
            if(terminalDisplay)
                sb.append(toFirst);
            sb.append(previous);
        }

        for(int i = 0; i < unitCount; i++)
        {
            pageNo = startPageNo + i;
            if(pageNo > totalUnits)
                break;

            if(pageNo == currentPage)
            {
                sb.append("[<b>" + pageNo + "</b>]");
            }
            else
            {
                sb.append("<a href=\"JavaScript:goto(" + pageNo + ")\">[" + pageNo + "]</a>");
            }
        }

        int forwardPageCount = pageNo + 1;

        if(forwardPageCount <= totalUnits)
        {
            sb.append("<a href=\"JavaScript:goto(" + forwardPageCount + ")\">" + forward + "</a>&nbsp;&nbsp;");
            if(terminalDisplay)
                sb.append("<a href=\"JavaScript:goto(" + totalUnits + ")\">" + toEnd + "</a>&nbsp;");
        }
        else if(nextAndPrevDisplay)
        {
            sb.append(forward);
            if(terminalDisplay)
                sb.append(toEnd);
        }
        return sb.toString();
    }

    /**
     * 테이블 형태로 화면에 보여진는 페이징 html을 가져온다.<br>
     * 주어진 값에 따라 화면 구성을 다르게한다.
     *
     * @return linkHtmlByTable 테이블 형태로 화면에 보여진는 html.
     */
    public String getLinkHtmlByTable()
    {
        return getLinkHtmlByTable(true, false, false, null);
    }

    /**
     * 테이블 형태로 화면에 보여진는 페이징 html을 가져온다.<br>
     * 주어진 값에 따라 화면 구성을 다르게한다.
     *
     * @param isDiplayFirstAndLast : 처음으로(◁◁), 마지막으로(▷▷) 링크를 화면에 보여지게 할 것인가? True이면 보여진다.
     * @param isDisplayNoLink : 처음으로(◁◁), 마지막으로(▷▷), 이전(◁), 다음(▷) 링크를 링크값이 없어도 화면에 보여지게 할 것인가? True이면 보여진다.
     * @return LinkHtml 페이지 네비게이션 값
     */
    public String getLinkHtmlByTable(boolean isDiplayFirstAndLast, boolean isDisplayNoLink)
    {
        return getLinkHtmlByTable(isDiplayFirstAndLast, isDisplayNoLink, false, null);
    }

    /**
     * 테이블 형태로 화면에 보여진는 html을 가져온다.<br>
     * 주어진 값에 따라 화면 구성을 다르게한다.
     *
     * @param isDiplayFirstAndLast : 처음으로(◁◁), 마지막으로(▷▷) 링크를 화면에 보여지게 할 것인가? True이면 보여진다.
     * @param isDisplayNoLink : 처음으로(◁◁), 마지막으로(▷▷), 이전(◁), 다음(▷) 링크를 링크값이 없어도 화면에 보여지게 할 것인가? True이면 보여진다.
     * @param isEnd : 처음으로(◁◁), 마지막으로(▷▷), 이전(◁), 다음(▷) 링크를 center가 아닌 right, left로 해서 양쪽 끝으로 화면에.... 디스플레이한다.
     * @param strWidth : 테이블 넓이
     * @return LinkHtml 페이지 네비게이션 값
     */
    public String getLinkHtmlByTable(boolean isDiplayFirstAndLast, boolean isDisplayNoLink, boolean isEnd, String strWidth)
    {
        StringBuffer sb = new StringBuffer();
        if(currentPage < 0)
        {
            currentPage = 1;
        }
        else if(currentPage >= totalUnits)
        {
            currentPage = totalUnits;
        }

        startRecNo = (currentPage - 1) * rowsPerPage + 1;
        endRecNo = startRecNo + rowsPerPage - 1;
        endRecNo = (endRecNo >= totalCount) ? totalCount : endRecNo;

        int backPageNo = (currentPage - 1) / unitCount;// to determine back link to be displayed or not
        int startPageNo = backPageNo * unitCount + 1;
        int pageNo = startPageNo;

        String strPrevAlign = "right";
        String strNextAlign = "left";
        if(isEnd)
        {
            strPrevAlign = "left";
            strNextAlign = "right";
        }

        if(strWidth != null)
        {
            strWidth = "width=\"" + strWidth + "\"";
        }
        else
        {
            strWidth = "";
        }

        sb.append("<table border=\"0\" " + strWidth + "><tr align=\"center\" valign=\"middle\">");

        if(backPageNo >= 1)
        {
            if(isDiplayFirstAndLast)
                sb.append("<td align=\"" + strPrevAlign + "\"><a href=\"JavaScript:goto(1);\">" + toFirst + "</a>&nbsp;&nbsp;</td>");
            sb.append("<td align=\"" + strPrevAlign + "\"><a href=\"JavaScript:goto(" + (startPageNo - unitCount) + ")\">" + previous + "</a>&nbsp;</td>");
        }
        else
        {
            if(isDisplayNoLink)
            {
                if(isDiplayFirstAndLast)
                    sb.append("<td align=\"" + strPrevAlign + "\">" + toFirst + "&nbsp;&nbsp;</td>");
                sb.append("<td align=\"" + strPrevAlign + "\">" + previous + "&nbsp;</td>");
            }
        }

        sb.append("<td>");
        for(int i = 0; i < unitCount; i++)
        {
            pageNo = startPageNo + i;
            if(pageNo > totalUnits)
                break;

            if(pageNo == currentPage)
            {
                sb.append("[<b>" + pageNo + "</b>]");
            }
            else
            {
                sb.append("<a href=\"JavaScript:goto(" + pageNo + ")\">[" + pageNo + "]</a>");
            }
        }
        sb.append("</td>");
        int forwardPageCount = pageNo + 1;

        if(forwardPageCount <= totalUnits)
        {
            sb.append("<td align=\"" + strNextAlign + "\"><a href=\"JavaScript:goto(" + forwardPageCount + ")\">" + forward + "</a>&nbsp;&nbsp;</td>");
            if(isDiplayFirstAndLast)
                sb.append("<td align=\"" + strNextAlign + "\"><a href=\"JavaScript:goto(" + totalUnits + ")\">" + toEnd + "</a>&nbsp;</td>");
        }
        else
        {
            if(isDisplayNoLink)
            {
                sb.append("<td align=\"" + strNextAlign + "\">" + forward + "&nbsp;&nbsp;</td>");
                if(isDiplayFirstAndLast)
                    sb.append("<td align=\"" + strNextAlign + "\">" + toEnd + "&nbsp;</td>");
            }
        }
        sb.append("</tr></table>");
        return sb.toString();
    }
    
    /**
     * 
     * @param func
     * @return
     */
    public String getSimplePageLink(String func)
    {
    	StringBuffer sb = new StringBuffer();
    	int backPageNo = (currentPage -1) / unitCount;// to determine back link to be displayed or not
        int startPageNo = backPageNo  * unitCount + 1;
        int pageNo = startPageNo;

        if (totalUnits > 0)
        	sb.append("<li>" + currentPage + "/" + totalUnits + "&nbsp;</li>"); 
        
        sb.append("<li>");
        if(currentPage > 1)
        {
            sb.append("<a href=\"JavaScript:" + func + "(" + (currentPage-1) + ")\">");
        }
        sb.append("<img src=\"" + previous + "\" align='absmiddle'>");
        sb.append("</a>");
        sb.append("</li>");
        

        int forwardPageCount = currentPage + 1;

        sb.append("<li>");
        if(forwardPageCount <= totalUnits)
        {
            sb.append("<a href=\"JavaScript:" + func + "(" + forwardPageCount + ")\">");
        }
        sb.append("<img src=\"" + forward + "\" align='absmiddle'>");
        sb.append("</a>");
        sb.append("</li>");
        
        return sb.toString();
    }

    public String getExtendPageLink()
    {
    	StringBuffer sb = new StringBuffer();
    	int backPageNo = (currentPage -1) / unitCount;// to determine back link to be displayed or not
        int startPageNo = backPageNo  * unitCount + 1;
        int pageNo = startPageNo;

        sb.append("<ul>");
        if (currentPage > 1)
            sb.append("<li><a href=\"?page=" + (currentPage-1) + "\">Prev</a></li>");
        else
            sb.append("<li class=\"disabled\"><a href=\"#\">Prev</a></li>");
        
        for(int i = 0; i < unitCount; i++)
        {
            pageNo = startPageNo + i;
            if(pageNo > totalUnits)
                break;

            if (pageNo == currentPage)
            {
                sb.append("<li class=\"disabled\"><a href=\"#\">" + pageNo + "</a></li>");
            }
            else
            {
                sb.append("<li class=\"active\"><a href=\"?page=" + pageNo + "\">" + pageNo + "</a></li>");
            }
        }
		
        int forwardPageCount = currentPage + 1;
        
		if(forwardPageCount <= totalUnits)
		    sb.append("<li class=\"active\"><a href=\"?page=" + forwardPageCount +  "\">Next</a></li>");
		else
		    sb.append("<li class=\"active\"><a href=\"#\">Next</a></li>");
		
		sb.append("</ul>");
        return sb.toString();
    }
    
    public String getPageLink(String func, String selectedClassName)
    {
    	setToFirstSymbol("&lsaquo;&lsaquo;");
		setPreviousSymbol("Prev");
		setForwardSymbol("Next");
		setToEndSymbol("&rsaquo;&rsaquo;");
		
    	StringBuffer sb = new StringBuffer();
    	int backPageNo = (currentPage -1) / unitCount;// to determine back link to be displayed or not
        int startPageNo = backPageNo  * unitCount + 1;
        int pageNo = startPageNo;

        if (currentPage > 1)
        	sb.append("<a href=\"javascript:" + func + "(1)\">" + toFirst + "</a>\n");
        else
        	sb.append("<span>" + toFirst + "</span>\n");
        	
        if (currentPage > 1)
        	sb.append("<a href=\"javascript:" + func + "(" + (currentPage-1) + ")\">" + previous + "</a>\n");
        else
        	sb.append("<span>" + previous + "</span>\n");
        
        
        for(int i = 0; i < unitCount; i++)
        {
            pageNo = startPageNo + i;
            if(pageNo > totalUnits)
                break;

            if (pageNo == currentPage)
            {
                sb.append("<a class=\"" + selectedClassName + "\">" + pageNo + "</a>\n");
            }
            else
            {
            	sb.append("<a href=\"javascript:" + func + "(" + pageNo + ")\">" + pageNo + "</a>\n");
            }
        }
		
        int forwardPageCount = currentPage + 1;
        
		if(forwardPageCount <= totalUnits)
			sb.append("<a href=\"javascript:" + func + "(" + forwardPageCount + ")\">" + forward + "</a>\n");
		else
			sb.append("<span>" + forward + "</span>\n");
		
		if(forwardPageCount <= totalUnits)
			sb.append("<a href=\"javascript:" + func + "(" + totalUnits + ")" + "\">" + toEnd + "</a>\n");
		else
			sb.append("<span>" + toEnd + "</span>\n");
		
        return sb.toString();
    }
    
    /**
     * 시작 레코드 번호를 가져온다.
     *
     * @return start 시작 레코드 번호.
     */
    public int getStart()
    {
        return startRecNo;
    }

    /**
     * 한페이지 나오는 글 개수를 가져온다.
     *
     * @return rowsPerPage 한페이지 나오는 글 개수.
     */
    public int getRowsPerPage()
    {
        return rowsPerPage;
    }

    /**
     * 마지막 레코드 번호를 가져온다.
     *
     * @return end 마지막 레코드 번호.
     */
    public int getEnd()
    {
        return endRecNo;
    }

	public String getToFirstClassName() 
	{
		return toFirstClassName;
	}

	public void setToFirstClassName(String toFirstClassName) 
	{
		this.toFirstClassName = toFirstClassName;
	}

	public String getToEndClassName() 
	{
		return toEndClassName;
	}

	public void setToEndClassName(String toEndClassName) 
	{
		this.toEndClassName = toEndClassName;
	}

	public String getPreviousClassName() 
	{
		return previousClassName;
	}

	public void setPreviousClassName(String previousClassName) 
	{
		this.previousClassName = previousClassName;
	}

	public String getForwardClassName() 
	{
		return forwardClassName;
	}

	public void setForwardClassName(String forwardClassName) 
	{
		this.forwardClassName = forwardClassName;
	}

	public String getCurrentPageClassName() 
	{
		return currentPageClassName;
	}

	public void setCurrentPageClassName(String currentPageClassName) 
	{
		this.currentPageClassName = currentPageClassName;
	}
    
    
}