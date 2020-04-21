package com.kuir.library.controller;

import com.kuir.library.bean.Book;
import com.kuir.library.bean.Jieshu;
import com.kuir.library.bean.User;
import com.kuir.library.dao.BookDao;
import com.kuir.library.dao.Jie;
import com.kuir.library.dao.UserDao;
import com.kuir.library.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    Jie jie;

    @RequestMapping("/userinfo")
    public String userinfo(){
        return "user/userinfo";
    }
    //查询图书
    @RequestMapping("/seebook")
    public String books(Book book, Model model) {
        List<Book> all = bookDao.findAll();
        List<Book> bookn = bookDao.findAllByBookname(book.getBookname());
        List<Book> pub = bookDao.findAllByPublishing(book.getPublishing());
        List<Book> leix = bookDao.findAllByLeixing(book.getLeixing());
        List<Book> publeix = bookDao.findAllByPublishingAndLeixing(book.getPublishing(), book.getLeixing());
        List<Book> publeixbook = bookDao.findAllByPublishingAndLeixingAndBookname(book.getPublishing(), book.getLeixing(),book.getBookname());
        List<Book> pl=bookDao.findAllByPublishingOrLeixing(book.getPublishing(), book.getLeixing());

        List<Book> plx=bookDao.querypubleix(book.getPublishing(), book.getLeixing());
        List<Book> plb=bookDao.queryplb(book.getPublishing(), book.getLeixing(), book.getBookname());
        System.out.println("10"+plx);
        if (plx.size()>0){
            model.addAttribute("book", plx);
            System.out.println("11"+plx);
            return "user/library";
        }
        else if (book.getPublishing() != "") {
            model.addAttribute("book", pub);
            System.out.println("1");
            return "user/library";
        } else if (book.getLeixing() != "") {
            model.addAttribute("book", leix);
            System.out.println("2");
            return "user/library";
        } else if (book.getBookname() != "") {
            model.addAttribute("book", bookn);
            return "user/library";
        }

        else {
            model.addAttribute("book", all);
            System.out.println(all+"123");
           return  "user/library";
        }
    }

    @RequestMapping("/updateA")
    public String updateA(Integer id,Model model){
        User userById = userDao.findUserById(id);
        model.addAttribute("username",userById);
        return "user/uddate";
    }
//修改个人信息
    @RequestMapping("/dateMe")
    public  String update(User user){
        System.out.println(user);
        userDao.update(user.getUsername(),user.getPassword(),user.getSex(),user.getSid(),user.getAdministrator(), user.getId());
        return "redirect:/toUser";
    }
    @RequestMapping("/toUser")
    public String toUser(Model model,HttpSession session){
        Integer huin = (Integer) session.getAttribute("huin");
        System.out.println(huin);
        User userById = userDao.findUserById(huin);
        System.out.println(userById);
        model.addAttribute("user",userById);
        return "user/userinfo";
    }
    /**
     * 借书
     */
    @RequestMapping("/bor")
    public String bor(){
        return "user/library";
    }
    @RequestMapping("/borrow")
    public String borrow(Book book, HttpSession session,@RequestParam(value = "id") Integer id,@RequestParam(value = "isbn")String isbn,@RequestParam(value = "bookname")String bookname){
        //从登录中获取用户信息
        User ssd = (User) session.getAttribute("user");
        ssd.getSid();
        System.out.println("dsa"+id+isbn+bookname);
        Jieshu jieshu = new Jieshu();
        jieshu.setId(id);
        jieshu.setBookname(bookname);
        jieshu.setIsbn(isbn);
        jieshu.setSid(ssd.getSid());
        jieshu.setLoan(1);
        jieshu.setBookid(book.getId());
        jieshu.setDate(new Date());
        jie.save(jieshu);
        bookDao.updateloan(book.getId());
        return "redirect:/bor";
    }
    /**
     * 查询jieshu
     */
    @RequestMapping("/findjieshu")
    public String findjieshu(HttpSession session,Model model,Integer id,Book book){
        User stu = (User) session.getAttribute("user");
        ArrayList<Jieshu> bySid = jie.findBySid(stu.getSid());
        model.addAttribute("jie",bySid);
        return "user/mine";
    }
    /**
     * 还书
     */
    @RequestMapping("/remine")
    public String remine(Integer id,Integer bid){
        jie.deleteAllById(id);
        bookDao.updatedown(bid);
        return "redirect:/findjieshu";
    }
    /**
     * 拦截器
     */
//test
    @RequestMapping("/testone")
    public List<Book> testone(){
        List<Book> all = bookDao.findAll();
        return all;
    }
    @RequestMapping("/test2")
    public String test(){
        return "htmlone";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/forgetpasswords")
    public String forgetpassword(){
        return "user/uddatepassword";
    }
    @RequestMapping("/upPass")
    public  String upPass(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password")String password,@RequestParam(value = "sid")Integer sid,
                          Model model){
        List<User> bySidAndUsername = userDao.findBySidAndUsername(sid, username);
        System.out.println(bySidAndUsername);
        System.out.println(username+password+sid);
        if (bySidAndUsername.size()>0){
            userDao.uddatepassword(password,sid);
            model.addAttribute("xgcg",1);
            System.out.println("002");
        }return "redirect:/login";

    }

}
