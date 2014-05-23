package kr.co.rudaks.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{    
    @RequestMapping(value = "/home", method = RequestMethod.GET)    
    public String home(HttpServletRequest request, Model model) throws Exception
    {
        //return new ModelAndView("home");
        return "home";
    }
}
