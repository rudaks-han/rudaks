package kr.co.rudaks.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.rudaks.web.WebConfig;
import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.bean.GeoipCountryForm;
import kr.co.rudaks.web.bean.GuestbookForm;
import kr.co.rudaks.web.bean.NotifyMessage;
import kr.co.rudaks.web.service.IGeoipCountryService;
import kr.co.rudaks.web.service.IPostService;
import kr.co.rudaks.web.util.BoardNavigatorByCount;
import kr.co.rudaks.web.util.CookieUtil;
import kr.co.rudaks.web.util.DateUtil;
import kr.co.rudaks.web.util.StringEscapeUtil;
import kr.co.rudaks.web.util.Util;
import kr.co.rudaks.web.validator.GuestbookDeleteValidator;
import kr.co.rudaks.web.validator.GuestbookModifyValidator;
import kr.co.rudaks.web.validator.GuestbookNewValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController extends CommonController
{    
    @Autowired
    public GuestbookNewValidator guestbookNewValidator;
    
    @Autowired
    public GuestbookModifyValidator guestbookModifyValidator;
    
    @Autowired
    public GuestbookDeleteValidator guestbookDeleteValidator;
    
    @Autowired
    private IPostService postService;
    
    @Autowired
    private IGeoipCountryService geoipCountryService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)    
    public String guestbook(@ModelAttribute GuestbookForm guestbookForm, BindingResult bindingResult, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
        
        guestbookForm.setRowsPerPage(Util.str2i(WebConfig.getString("guestbook.list.count"), 20));
        int page = guestbookForm.getPage();
        if (page < 1)
            page = 1;
        
        int startNo = (page-1)*guestbookForm.getRowsPerPage();
        int endNo = (page-1)*guestbookForm.getRowsPerPage() + guestbookForm.getRowsPerPage();
        
        guestbookForm.setStartNo(startNo);
        guestbookForm.setEndNo(endNo);
        
        int totalCount = postService.selectGuestbookListCount(guestbookForm);
        
        List<GuestbookForm> guestbookList = postService.selectGuestbookList(guestbookForm);
        if (guestbookList != null)
        {
            for (int i=0; i<guestbookList.size(); i++)
            {
                GuestbookForm guestbookFormObj = (GuestbookForm) guestbookList.get(i);
                guestbookFormObj.setComment(StringEscapeUtil.escapeXSS(guestbookFormObj.getComment()));
            }
            
            model.addAttribute("guestbookList", guestbookList);
            
            BoardNavigatorByCount nav = new BoardNavigatorByCount(totalCount, guestbookForm.getRowsPerPage(), page);
            model.addAttribute("navLinkHtml", nav.getExtendPageLink());
        }
        
        return "guestbook";
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)    
    public String guestbookAction(@ModelAttribute GuestbookForm guestbookForm, HttpServletRequest request, RedirectAttributes redirectAttr, 
    		BindingResult bindingResult, Model model) throws Exception
    {
        getNavigatorMenu(model); // 카테고리 리스트 가져오기
            
        guestbookNewValidator.validate(guestbookForm, bindingResult);
        
        //System.err.println("files : " + postForm.getFiles());
        // 입력값 검증
        if (bindingResult.hasErrors())
        {
            checkValidation(bindingResult, model);
            
            return "guestbook.new";
        }   
        
        GeoipCountryForm geoipCountryForm = geoipCountryService.selectCountryByIp(request.getRemoteAddr());
        System.err.println("::: " + geoipCountryForm.getCountryCode());
        
        // 한국이 아니라면 글을 못쓰게 한다.
        if (!"KR".equals(geoipCountryForm.getCountryCode()))
        {
        	redirectMessage(redirectAttr, "You can not post a guest book.", "error");
        	return "redirect:/guestbook";
        }
        String currDate = DateUtil.getCurrDate("yyyyMMddHHmmss");
        String remoteAddr = request.getRemoteAddr();
        
        int seq = postService.selectNextSeq("seq_guestbook");
        
        guestbookForm.setId(seq);
        if (guestbookForm.getRef() < 1)
            guestbookForm.setRef(seq);
        guestbookForm.setCreatedDate(currDate);
        guestbookForm.setUpdatedDate(currDate);
        guestbookForm.setIpaddress(remoteAddr);
        postService.insertGuestbook(guestbookForm);
        
        if (guestbookForm.getRef() > 0)
            return "redirect:/guestbook#post-" + guestbookForm.getRef();
        return "redirect:/guestbook";
    }
    
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)    
    public String guestbookModify(HttpServletRequest request, HttpSession session, @PathVariable(value = "id") int id, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        GuestbookForm guestbookForm = postService.selectGuestbook(id);
        
        if (guestbookForm == null)
        {
            redirectMessage(redirectAttr, "해당 글이 없습니다.", "error");
            
            return "redirect:/guestbook";
        }
        
        model.addAttribute("guestbookForm", guestbookForm);
        return "guestbook.modify";
    }
    
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)    
    public String guestbookModifyAction(@ModelAttribute GuestbookForm guestbookForm, @PathVariable(value = "id") int id, HttpSession session, 
                    BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        GuestbookForm guestbookFormObj = postService.selectGuestbook(id);
        
        if (guestbookFormObj == null)
        {
            redirectMessage(redirectAttr, "해당 글이 없습니다.", "error");
            
            return "redirect:/guestbook";
        }
        
        guestbookModifyValidator.validate(guestbookForm, bindingResult);
        
        //System.err.println("files : " + postForm.getFiles());
        // 입력값 검증
        if (bindingResult.hasErrors())
        {
            checkValidation(bindingResult, model);
            
            return "guestbook.modify";
        }
        
        if (!guestbookForm.getPassword().equals(guestbookFormObj.getPassword()))
        {
            List<NotifyMessage> notifyMessageList = new ArrayList<NotifyMessage>();
            notifyMessageList.add(new NotifyMessage("비밀번호가 맞지 않습니다.", "error", "#" + "password"));
            model.addAttribute("notifyMessage", notifyMessageList);
            return "guestbook.modify";
        }
        
        String currDate = DateUtil.getCurrDate("yyyyMMddHHmmss");
        guestbookForm.setUpdatedDate(currDate);
        postService.updateGuestbook(guestbookForm);
        
        redirectMessage(redirectAttr, "수정되었습니다.", "info");
        
        return "redirect:/guestbook#post-" + guestbookFormObj.getRef();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)    
    public String guestbookDelete(HttpServletRequest request, HttpSession session, @PathVariable(value = "id") int id, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        GuestbookForm guestbookForm = postService.selectGuestbook(id);
        
        if (guestbookForm == null)
        {
            redirectMessage(redirectAttr, "해당 글이 없습니다.", "error");
            
            return "redirect:/guestbook";
        }
        
        model.addAttribute("guestbookForm", guestbookForm);
        return "guestbook.delete";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)    
    public String guestbookDeleteAction(@ModelAttribute GuestbookForm guestbookForm, @PathVariable(value = "id") int id, HttpSession session, HttpServletRequest request,
                    BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) throws Exception
    {
        GuestbookForm guestbookFormObj = postService.selectGuestbook(id);
        
        if (guestbookFormObj == null)
        {
            redirectMessage(redirectAttr, "해당 글이 없습니다.", "error");
            
            return "redirect:/guestbook";
        }
        
        model.addAttribute("guestbookForm", guestbookFormObj);
        
        guestbookDeleteValidator.validate(guestbookForm, bindingResult);
        
        //System.err.println("files : " + postForm.getFiles());
        // 입력값 검증
        if (bindingResult.hasErrors())
        {
            checkValidation(bindingResult, model);
            
            return "guestbook.delete";
        }   
        
        String userId = CookieUtil.getCookie(request, true, WebPublic.COOKIE_UID);
        if (WebPublic.ADMIN_ID.equals(userId))
        {   
           // admin이라면 삭제하게 한다.
        }
        else if (!guestbookForm.getPassword().equals(guestbookFormObj.getPassword()))
        {
            List<NotifyMessage> notifyMessageList = new ArrayList<NotifyMessage>();
            notifyMessageList.add(new NotifyMessage("비밀번호가 맞지 않습니다.", "error", "#" + "password"));
            model.addAttribute("notifyMessage", notifyMessageList);
            return "guestbook.delete";
        }
        
        String currDate = DateUtil.getCurrDate("yyyyMMddHHmmss");
        guestbookForm.setUpdatedDate(currDate);
        postService.deleteGuestbook(guestbookForm);
        
        redirectMessage(redirectAttr, "삭제되었습니다.", "info");
        
        return "redirect:/guestbook";
    }
    
}
