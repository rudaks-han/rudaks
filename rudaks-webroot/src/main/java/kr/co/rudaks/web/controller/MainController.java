package kr.co.rudaks.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import kr.co.rudaks.web.WebConfig;
import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.bean.AttachFileForm;
import kr.co.rudaks.web.bean.LoginForm;
import kr.co.rudaks.web.bean.PostForm;
import kr.co.rudaks.web.service.IAccessLogService;
import kr.co.rudaks.web.service.IPostService;
import kr.co.rudaks.web.util.BoardNavigatorByCount;
import kr.co.rudaks.web.util.CookieUtil;
import kr.co.rudaks.web.util.CryptoUtil;
import kr.co.rudaks.web.util.DateUtil;
import kr.co.rudaks.web.util.StringUtil;
import kr.co.rudaks.web.util.Util;
import kr.co.rudaks.web.validator.PostModifyValidator;
import kr.co.rudaks.web.validator.PostNewValidator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Controller
public class MainController extends CommonController
{    
	//private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	//final Logger logger = LoggerFactory.getLogger(MainController.class);
	private final static Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);
	
    @Autowired
    public PostNewValidator postNewValidator; 
    
    @Autowired
    public PostModifyValidator postModifyValidator; 
    
    @Autowired
    private IPostService postService;
    
    @Autowired
    private IAccessLogService accessLogService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)    
    public String index(@ModelAttribute PostForm postForm, HttpServletRequest request, Model model) throws Exception
    {
    	/*getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        postForm.setRowsPerPage(Util.str2i(WebConfig.getString("post.list.count"), 5));
        int page = postForm.getPage();
        if (page < 1)
            page = 1;
        
        int startNo = (page-1)*postForm.getRowsPerPage();
        int endNo = (page-1)*postForm.getRowsPerPage() + postForm.getRowsPerPage();
        
        postForm.setStartNo(startNo);
        postForm.setEndNo(endNo);
                
        int totalCount = postService.selectPostListCount(postForm);
        
        List<PostForm> postList = null;
        
        if (totalCount > 0)
        {
            postList = postService.selectPostList(postForm);

            for (int i=0; i<postList.size(); i++)
            {
                PostForm postFormTemp = (PostForm) postList.get(i);
                if (postFormTemp.getAttachCount() > 0)
                {
                    List<AttachFileForm> attachFileList = postService.selectAttachFileList(postFormTemp.getId());
                    postFormTemp.setAttachFileList(attachFileList);
                }
            }
            model.addAttribute("postList", postList);
            
            BoardNavigatorByCount nav = new BoardNavigatorByCount(totalCount, postForm.getRowsPerPage(), page);
            model.addAttribute("navLinkHtml", nav.getExtendPageLink());
        }
        return "home";*/
    	return "redirect:/home";
    } 
    
    /**
     * @defgroup home 메인화면
     * @intgroup main
     * 
     * 메인화면
     * 
     * @version v2
     * @par Method 
     * GET
     * @par URL 
     * /home
     * @par URL example:
     * /home
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)    
    public String home(@ModelAttribute PostForm postForm, HttpServletRequest request, Model model) throws Exception
    { 
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        postForm.setRowsPerPage(Util.str2i(WebConfig.getString("post.list.count"), 5));
        int page = postForm.getPage();
        if (page < 1)
            page = 1;
        
        int startNo = (page-1)*postForm.getRowsPerPage();
        int endNo = (page-1)*postForm.getRowsPerPage() + postForm.getRowsPerPage();
        
        postForm.setStartNo(startNo);
        postForm.setEndNo(endNo);
                
        int totalCount = postService.selectPostListCount(postForm);
        
        List<PostForm> postList = null;
        
        if (totalCount > 0)
        {
            postList = postService.selectPostList(postForm);

            for (int i=0; i<postList.size(); i++)
            {
                PostForm postFormTemp = (PostForm) postList.get(i);
                if (postFormTemp.getAttachCount() > 0)
                {
                    List<AttachFileForm> attachFileList = postService.selectAttachFileList(postFormTemp.getId());
                    postFormTemp.setAttachFileList(attachFileList);
                }
            }
            model.addAttribute("postList", postList);
            
            BoardNavigatorByCount nav = new BoardNavigatorByCount(totalCount, postForm.getRowsPerPage(), page);
            model.addAttribute("navLinkHtml", nav.getExtendPageLink());
        }
        
        return "home";
    }
    
    public void getNavigatorMenu(Model model)
    {
        // 카테고리 리스트 가져오기
        List <HashMap<String, Object>> categoryList = postService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        
        List <HashMap<String, Object>> categoryListByCount = postService.selectCategoryListByCount();
        model.addAttribute("categoryListByCount", categoryListByCount);
      
        // 최근 posts 리스트 가져오기
        List <PostForm> recentPostList = postService.selectRecentPostList(10);
        model.addAttribute("recentPostList", recentPostList);
    }
    
    @RequestMapping(value = "/list/{category}", method = RequestMethod.GET)    
    public String postByCategory(@ModelAttribute PostForm postForm, @PathVariable(value = "category") String category, HttpServletRequest request, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        postForm.setRowsPerPage(Util.str2i(WebConfig.getString("post.list.count"), 5));
        int page = postForm.getPage();
        if (page < 1)
            page = 1;
        
        int startNo = (page-1)*postForm.getRowsPerPage();
        int endNo = (page-1)*postForm.getRowsPerPage() + postForm.getRowsPerPage();
        
        postForm.setStartNo(startNo);
        postForm.setEndNo(endNo);
        
        int totalCount = postService.selectPostListCount(postForm);
        
        postForm.setCategory(category);
        
        List<PostForm> postList = postService.selectPostList(postForm);
                        
        model.addAttribute("postList", postList);
        
        BoardNavigatorByCount nav = new BoardNavigatorByCount(totalCount, postForm.getRowsPerPage(), page);
        model.addAttribute("navLinkHtml", nav.getExtendPageLink());
        return "list";
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)    
    public String post(HttpServletRequest request, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        return "post";
    }
    
    @RequestMapping(value = "/post-new", method = RequestMethod.GET)    
    public String postNew(@ModelAttribute PostForm postForm, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        return "post-new";
    }
    
    @RequestMapping(value = "/post-new", method = RequestMethod.POST)    
    public String postNewAction(@ModelAttribute @Valid PostForm postForm, HttpServletRequest request, BindingResult bindingResult, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        postNewValidator.validate(postForm, bindingResult);
        
        // 입력값 검증
        if (bindingResult.hasErrors())
        {
            checkValidation(bindingResult, model);
            
            return "post-new";
        }   
        
        String currDate = DateUtil.getCurrDate("yyyyMMddHHmmss");
        int attachCount = 0;
        String remoteAddr = request.getRemoteAddr();
        
        // 첨부파일이 있으면 입력
        if (postForm.getFilePath() != null && postForm.getFilePath().length() > 0)
        {
            String [] tmpFilePath = StringUtils.split(postForm.getFilePath(), ",");
            if (tmpFilePath.length > 0)
                attachCount = tmpFilePath.length;
            
            if (attachCount > 0)
            {
                String tmpDir = WebConfig.getString("attach.tmp.dir");
                String uploadDir = WebConfig.getString("attach.upload.dir");
                
                String [] arFilePath = StringUtils.split(postForm.getFilePath(), ",");
                String [] arFileName = StringUtils.split(postForm.getFileName(), ",");
                String [] arFileSize = StringUtils.split(postForm.getFileSize(), ",");
                
                List<AttachFileForm> attachFileList = new ArrayList<AttachFileForm>();
                for (int i=0; i<attachCount; i++)
                {
                    AttachFileForm attachFileForm = new AttachFileForm();
                    attachFileForm.setSeq(i);
                    attachFileForm.setFileName(arFileName[i]);
                    attachFileForm.setFilePath(arFilePath[i]);
                    attachFileForm.setFileSize(Long.parseLong(arFileSize[i]));
                    attachFileList.add(attachFileForm);
                    
                    File file = new File(tmpDir + "/" + arFilePath[i]);
                    if (file.exists())
                        file.renameTo(new File(uploadDir + "/" + arFilePath[i]));
                }
                
                postForm.setAttachFileList(attachFileList);
            }
        }
        
        postForm.setCategory(postForm.getCategory());
        postForm.setViewCount(0);
        postForm.setAttachCount(attachCount);
        postForm.setIpaddress(remoteAddr);
        postForm.setDeleteFlag("N");
        postForm.setCreatedDate(currDate);
        postForm.setUpdatedDate(currDate);
        
        int postId = postService.insertPost(postForm);
               
        if (postId > 0)
            return "redirect:/post/" + postId;
        else
            return "post-new";
    }
    
    @RequestMapping(value = "/post-modify/{id}", method = RequestMethod.GET)    
    public String postModify(@PathVariable(value = "id") int id, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        PostForm postForm = postService.selectPost(id);
        
        if (postForm != null)
        {   
            String filePath = "";
            String fileName = "";
            String fileSize = "";
            if (postForm.getAttachCount() > 0)
            {
                List<AttachFileForm> attachFileList = postService.selectAttachFileList(id);
                postForm.setAttachFileList(attachFileList);
                
                if (attachFileList != null && attachFileList.size() > 0)
                {
                    for (int i=0; i<attachFileList.size(); i++)
                    {
                        if (i>0)
                        {
                            filePath += ",";
                            fileName += ",";
                            fileSize += ",";
                        }
                        AttachFileForm attachFileForm = (AttachFileForm) attachFileList.get(i);
                        filePath += attachFileForm.getFilePath();
                        fileName += attachFileForm.getFileName();
                        fileSize += attachFileForm.getFileSize();
                    }
                }
            }
            
            postForm.setFilePath(filePath);
            postForm.setFileName(fileName);
            postForm.setFileSize(fileSize);
            
            model.addAttribute("postForm", postForm);
        }
        
        return "post-modify";
    }
    
    @RequestMapping(value = "/post-modify/{id}", method = RequestMethod.POST)    
    public String postModifyAction(@ModelAttribute @Valid PostForm postForm, @PathVariable(value = "id") int id, HttpServletRequest request, 
                    BindingResult bindingResult, Model model) throws Exception
    { 
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        postModifyValidator.validate(postForm, bindingResult);
        // 입력값 검증
        if (bindingResult.hasErrors())
        {
            checkValidation(bindingResult, model);
            
            return "post-modify";
        }   
        
        String currDate = DateUtil.getCurrDate("yyyyMMddHHmmss");
        int attachCount = 0;
        String remoteAddr = request.getRemoteAddr();
        
        // 첨부파일이 있으면 입력
        if (postForm.getFilePath() != null && postForm.getFilePath().length() > 0)
        {
            String [] tmpFilePath = StringUtils.split(postForm.getFilePath(), ",");
            if (tmpFilePath.length > 0)
                attachCount = tmpFilePath.length;
            
            if (attachCount > 0)
            {
                String tmpDir = WebConfig.getString("attach.tmp.dir");
                String uploadDir = WebConfig.getString("attach.upload.dir");
                
                String [] arFilePath = StringUtils.split(postForm.getFilePath(), ",");
                String [] arFileName = StringUtils.split(postForm.getFileName(), ",");
                String [] arFileSize = StringUtils.split(postForm.getFileSize(), ",");
                
                List<AttachFileForm> attachFileList = new ArrayList<AttachFileForm>();
                for (int i=0; i<attachCount; i++)
                {
                    AttachFileForm attachFileForm = new AttachFileForm();
                    attachFileForm.setSeq(i);
                    attachFileForm.setFileName(arFileName[i]);
                    attachFileForm.setFilePath(arFilePath[i]);
                    attachFileForm.setFileSize(Long.parseLong(arFileSize[i]));
                    attachFileList.add(attachFileForm);
                    
                    File file = new File(tmpDir + "/" + arFilePath[i]);
                    if (file.exists())
                        file.renameTo(new File(uploadDir + "/" + arFilePath[i]));
                }
                
                postForm.setAttachFileList(attachFileList);
            }
        }
        
        postForm.setCategory(postForm.getCategory());
        postForm.setViewCount(0);
        postForm.setAttachCount(attachCount);
        postForm.setIpaddress(remoteAddr);
        postForm.setDeleteFlag("N");
        postForm.setCreatedDate(currDate);
        postForm.setUpdatedDate(currDate);
        
        int postId = postService.updatePost(postForm);
        
        return "redirect:/post/" + postId;
    }
    
    @RequestMapping(value = "/post-delete/{id}", method = RequestMethod.POST)    
    public String postDeleteAction(@PathVariable(value = "id") int id, HttpServletRequest request, 
                    RedirectAttributes redirectAttr, Model model) throws Exception
    {
        PostForm postForm = postService.selectPost(id);
        
        if (postForm != null)
        {
            postService.deletePost(id);
             
            redirectMessage(redirectAttr, "해당 글이 삭제되었습니다.", "info");
            
            return "redirect:/list/" + postForm.getCategory();
        }
        
        return "redirect:/home";
    }
    
    @ResponseBody
    @RequestMapping(value="/upload-attach", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
    public String uploadAttach(@RequestParam("attachFile") MultipartFile attachFile) throws IOException 
    {
        //HttpSession session = request.getSession();
        /*loginInfo loginInfo = (LoginInfo) session.getAttribute(WebPublic.SESSION_LOGIN_INFO);*/
                

        String tmpFilesDir = WebConfig.getString("attach.tmp.dir");
        
        String originalfileName = attachFile.getOriginalFilename();
        
        long fileSize = attachFile.getSize();       
        String fileExt = FilenameUtils.getExtension(originalfileName).toLowerCase();
        
        // 확장자는 png, jpg, gif, zip 종류만 허용
        /*boolean bAllowFileExt = false;
        String allowFileExt = WebConfig.getString("email.allow.fileext");
        String [] arAllowFileExtList = allowFileExt.split(",");
        if (arAllowFileExtList != null && arAllowFileExtList.length > 0)
        {
            for (int i=0; i<arAllowFileExtList.length; i++)
            {
                if (fileExt.endsWith(arAllowFileExtList[i]))
                {
                    bAllowFileExt = true;
                    break;
                }
            }
        }
        
        if (!bAllowFileExt) // 허용하는 확장자가 아니라면
        {
            AttachFileForm attachFileForm = new AttachFileForm();
            attachFileForm.setSuccess(false);
            attachFileForm.setMsg(context.getMessage("field.mail.invalid.fileext", null, Locale.US));           
            //return attachFileForm;
            throw new Exception();
        }*/
                
        String guid = Util.getGuid();
        guid = guid.replaceAll(":", "_");
        String fileName = guid + "." + fileExt;
        
        File directory = new File(tmpFilesDir);
        if (!directory.exists())
            FileUtils.forceMkdir(directory);
        
        File destFile = new File(tmpFilesDir + "/" + fileName);
        
        try
        {
            attachFile.transferTo(destFile);
            destFile = null;
        }
        catch (Exception e)
        {
        	logger.error("error", e);
        }
        System.out.println("file upload is end.");
        
        AttachFileForm attachFileForm = new AttachFileForm();
        attachFileForm.setSuccess(true);
        attachFileForm.setMsg("");
        attachFileForm.setFilePath(fileName);
        attachFileForm.setFileName(originalfileName);
        attachFileForm.setFileSize(fileSize);
        
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(attachFileForm);
     }
    
    /**
     * @defgroup post 게시물 가져오기
     * @intgroup posts
     * 
     * 게시물 가져오기
     * 
     * @version v2
     * @par Method 
     * GET
     * @par URL 
     * /post/{id}
     * @par URL example:
     * /post/1
     * @par Request type:
     * N/A
     * @par Request value definition:
     * * @b id (@e Integer, @e Required) post 아이디
     * @par Request example:
     * <pre>
     * /post/1
     * </pre> 
     */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)    
    public String post(HttpServletRequest request, @PathVariable(value = "id") int id, @RequestParam(value = "forward", required = false) String forward, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        PostForm postForm = null;
        
        if ("1".equals(forward)) // 이전 게시판에서 seq값이 넘어올때
            postForm = postService.selectPostBySeq(id);
        else
            postForm = postService.selectPost(id);
        
        if (postForm != null)
        {   
            if (postForm.getAttachCount() > 0)
            {
                List<AttachFileForm> attachFileList = postService.selectAttachFileList(id);
                postForm.setAttachFileList(attachFileList);
            }
            model.addAttribute("postForm", postForm);
            model.addAttribute("pageTitle", postForm.getTitle() + " | Rudaks.co.kr");
        }
        return "post";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) throws Exception
    {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(LoginForm loginForm, HttpSession session, HttpServletResponse response, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        if (WebPublic.ADMIN_ID.equals(loginForm.getUserId()) && WebPublic.ADMIN_PWD.equals(loginForm.getPassword()))
        {
            String encryptedValue = CryptoUtil.encrypt(WebPublic.SHA_SALT_KEY, loginForm.getUserId());
            CookieUtil.setCookie(response, WebPublic.COOKIE_UID, encryptedValue);
            
            if (loginForm.getRetUrl() == null || loginForm.getRetUrl().length() == 0)
                return "redirect:/";
            else
                return "redirect:" + loginForm.getRetUrl();
        }
        else
        {
            redirectMessage(redirectAttr, "아이디와 비밀번호가 맞지 않습니다.", "error");
            
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        CookieUtil.setCookie(response, WebPublic.COOKIE_UID, "");
        redirectMessage(redirectAttr, "로그아웃 되었습니다.", "info");
        
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/octet-stream")
	@ResponseBody
	public byte[] download(@RequestParam(required = false, value = "filepath") String filePath, @RequestParam(required = false, value = "filename") String fileName, 
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{
    	byte[] bytes = null;
    	
		if (fileName == null)
		{
			return null;
		}
		else
		{
			filePath = StringUtil.replaceAll(filePath, "..", "");
			fileName = StringUtil.replaceAll(fileName, "..", "");
		}
		
	    try
		{
			//commonService.insertDownloadLog(request, osType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	    
	    String uploadDir = WebConfig.getString("attach.upload.dir");
		File file = new File(uploadDir + "/" + filePath);
		if (file.exists())
		{ 
			bytes = FileCopyUtils.copyToByteArray(file);
		}

		DBObject info = new BasicDBObject();
        info.put("session_id", request.getSession(true).getId());
        info.put("created_date", DateUtil.getCurrDate("yyyyMMddHHmmss"));
        info.put("ip", request.getRemoteAddr());
        info.put("filename", fileName);
        info.put("filePath", filePath);
        accessLogService.createDownloadLog(info.toString());
        
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	    response.setContentLength(bytes.length);
	    response.setContentType("application/octet-stream");
	    return bytes;
	}
    
    @RequestMapping(value = "/404", method = RequestMethod.GET)

    public String error_404() throws Exception
    {
        return "error.404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String error_500() throws Exception
    {
        return "error.500";
    }
}
