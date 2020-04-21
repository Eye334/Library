package com.kuir.library.controller;

import com.kuir.library.bean.Book;
import com.kuir.library.bean.User;
import com.kuir.library.dao.BookDao;
import com.kuir.library.dao.UserDao;
import com.kuir.library.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;
    @RequestMapping("/login")
//    @ResponseBody 返回字符串需要
    public String login(){
        return"login";
    }
    @RequestMapping("/log")

    public String log(){
        return"login";
    }
    //图书馆查看所有
    @RequestMapping("/stulibrary")
    public String stulibrary(Model model){
        List<Book> all = bookDao.findAll();

        List<Book> updateo = bookDao.updateo();
        List<Book> updatetwo = bookDao.updatetwo();
        List<Book> updatethree = bookDao.updatethree();
        List<Book> updatefour = bookDao.updatefour();
        model.addAttribute("book",updateo);
        model.addAttribute("two",updatetwo);
        model.addAttribute("three",updatethree);
        model.addAttribute("four",updatefour);
        return "user/library";
    }
    //用户信息
    @RequestMapping("/stuinfo")
    public String stuinfo(){
        return "user/userinfo";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
    @RequestMapping("/forgetpassword")
    public String forgetthepassword(){
        return "forgetpassword";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    //管理员登录后返回
    @RequestMapping("/fanhui")
    public String fanhui(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "admin";
    }
    //返回界面
    @RequestMapping("/tuihui")
    public String tui(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "login";
    }
    /**
     * 登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("doLogin")
    public String doLogin(User user, Model model, HttpSession session){
        User user1 = userDao.findAllByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(user1);
        if(user1 == null){
            model.addAttribute("dia",1);
            return "login";
        }
        else if (1==user1.getAdministrator()){
        model.addAttribute("user",user1);
        session.setAttribute("user",user1);
            System.out.println(user1.getUsername()+user1.getPassword()+"5");
            return "redirect:/bookInquireA";
        }
        else {
            session.setAttribute("user",user1);
            session.setAttribute("huin",user1.getId());
            System.out.println(user1.getUsername()+user1.getPassword()+"20");
            return "redirect:/toUser";
        }

    }
    //注册
    @RequestMapping("doRegist")
    public String doRegist(String username,String password,Integer sid,String sex,  Model model){
        User user=new User();
        User user1 = userDao.findAllByUsername(username);
//如果用户名不存在
        if (user1==null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setSid(sid);
            user.setSex(sex);
            user.setAdministrator(0);
            model.addAttribute("user", user);
            userDao.save(user);
            System.out.println(user);
            System.out.println(username);
            System.out.println(password);
            System.out.println(sid);
            System.out.println(sex);
            return "login";
        }else{
            userService.save(user);
            model.addAttribute("cun",1);
            return "register";
        }
    }
    @RequestMapping("/dolog")
    public String dolog(String username,String password){
        User ds = userDao.findUserByUsernameAndPassword(username, password);
        if (ds!=null){
            return "redirect:/toUser";
        }
        return "login";
    }

}
