package kr.co.rudaks.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import kr.co.rudaks.web.bean.NotifyMessage;
import kr.co.rudaks.web.bean.PostForm;
import kr.co.rudaks.web.service.IPostService;
import kr.co.rudaks.web.util.BoardNavigatorByCount;
import kr.co.rudaks.web.validator.GuestbookDeleteValidator;
import kr.co.rudaks.web.validator.GuestbookModifyValidator;
import kr.co.rudaks.web.validator.GuestbookNewValidator;
import kr.co.rudaks.web.validator.PostModifyValidator;
import kr.co.rudaks.web.validator.PostNewValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class CommonController
{    
    @Autowired
    public PostNewValidator postNewValidator; 
    
    @Autowired
    public PostModifyValidator postModifyValidator; 
    
    @Autowired
    public GuestbookNewValidator guestbookNewValidator;
    
    @Autowired
    public GuestbookModifyValidator guestbookModifyValidator;
    
    @Autowired
    public GuestbookDeleteValidator guestbookDeleteValidator;
    
    @Autowired
    private IPostService postService;
    
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
    
    public void checkValidation(BindingResult bindingResult, Model model)
    {
        List<NotifyMessage> notifyMessageList = new ArrayList<NotifyMessage>();
        
        List<FieldError> list = bindingResult.getFieldErrors();
        for (int i=0; i<list.size();i++)
        {
            FieldError fe = (FieldError) list.get(i);
            notifyMessageList.add(new NotifyMessage(fe.getDefaultMessage(), "error", "#" + fe.getField()));
        }
        
        model.addAttribute("notifyMessage", notifyMessageList);
    }
    
    public void redirectMessage(RedirectAttributes redirectAttr, String msg, String msgType)
    {
        List<NotifyMessage> notifyMessageList = new ArrayList<NotifyMessage>();
        notifyMessageList.add(new NotifyMessage(msg, msgType));
        redirectAttr.addFlashAttribute("notifyMessage", notifyMessageList);
    }
}
