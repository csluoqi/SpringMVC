package jdbcStudy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcStudy.domain.Usr;
import jdbcStudy.service.LoginService;
import jdbcStudy.urlSet.RequestUrl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction
{
    private static Logger log = Logger.getLogger(LoginAction.class);
    @Autowired
    private LoginService loginService;
    @RequestMapping(value="/login")
    public String login(Model model)
    {
        model.addAttribute("user", new Usr());
        return "jdbcStudy_login";
    }
    
    @RequestMapping(value="/loginAction")
    public String  loginAction(@ModelAttribute("user") Usr user)
    {
        log.info(user.getName());
        log.info(user.getPassword());
        //查询数据库验证是否合法,不合法的话跳转到不合法页面,合法的话跳转到合法的页面
        boolean validUser = loginService.verifyUser(user);
        if (validUser)
        {
            return "redirect:"+RequestUrl.BOOK_INFO;
        }
        else
        {
            return "jdbcStudy_errorInfo";
        }
    }

}
