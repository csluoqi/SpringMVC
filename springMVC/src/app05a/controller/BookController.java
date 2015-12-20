package app05a.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app05a.domain.Book;
import app05a.domain.Category;
import app05a.service.BookService;
import app05a.service.TokenProccessor;

@Controller
public class BookController {

    private static Logger logg = Logger.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @Autowired
    private TokenProccessor tokenProccessor;
    
    private static final Log logger = LogFactory.getLog(BookController.class);

    @RequestMapping(value = "/book_input")
    public String inputBook(HttpServletRequest request,Model model) {
        String clientToken = tokenProccessor.getInstance().makeToken();
        request.getSession().setAttribute("token", clientToken);
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "BookAddForm";
    }

    @RequestMapping(value = "/book_edit/{id}")
    public String editBook(Model model, @PathVariable long id) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        Book book = bookService.get(id);
        model.addAttribute("book", book);
        return "BookEditForm";
    }

    @RequestMapping(value = "/book_save")
    public String saveBook(HttpServletRequest request ,@ModelAttribute Book book) 
    {
        boolean isrepeatSubmit = bookService.isRepeatSubmit(request);
        //如果是重复提交应该如何处理,不加入数据就可以了不要return null,return会使页面报异常
        if(isrepeatSubmit)
        {
            logger.info("is repeat submit");
        }
        
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.save(book);
        return "redirect:/book_list";
    }

    /**
     * 用于测试防止重复提交的类
     * @param request request
     * @param response response
     */
    @RequestMapping(value = "/savebook")
    public void saveBook(HttpServletRequest request,HttpServletResponse response)
    {
        //logger.info("into saveBook");
        boolean isrepeatSubmit = bookService.isRepeatSubmit(request);
        //如果是重复提交应该如何处理,不加入数据就可以了不要return null,return会使页面报异常
        if(isrepeatSubmit)
        {
            logger.info("is repeat submit");
        }
        else
        {
            logger.info("first submit");
        }
    }
    @RequestMapping(value = "/book_update")
    public String updateBook(@ModelAttribute Book book) {
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.update(book);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_list")
    public String listBooks(Model model) {
        logg.info("log4jlog4jlog4jlog4jlog4j");
        logg.error("log123456");
        logger.info("book_list");
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "BookList";
    }
}