package jdbcStudy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcStudy.domain.Bok;
import jdbcStudy.service.BokService;
import jdbcStudy.urlSet.PagePath;
import jdbcStudy.urlSet.RequestUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookAcion
{
    @Autowired
    private BokService bookService;
    
    @RequestMapping(value=RequestUrl.BOOK_INFO)
    public String findAllBook(HttpServletRequest request,
            HttpServletResponse response,Model model)
    {
        List<Bok> bookList = bookService.findAllBook();
        model.addAttribute("bookList", bookList);
     return PagePath.JDBCSTUDY_BOOK;   
    }
    
}
