package com.kuir.library.controller;
import com.kuir.library.bean.Book;
import com.kuir.library.bean.Jieshu;
import com.kuir.library.dao.Jie;
import com.kuir.library.bean.User;
import com.kuir.library.dao.BookDao;
import com.kuir.library.dao.UserDao;
import com.kuir.library.server.BookService;
import com.kuir.library.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserService userService;
    @Autowired
    Jie jie;
    @Autowired
    UserDao userDao;


//添加书本
    @RequestMapping("/addbook")
    public String admin(Book book, Model model) {
        Book addbook = bookService.getIsbn(book);
        bookService.save(addbook);
        return "redirect:/bookInquireA";
    }

    @RequestMapping("/adminaddbook")
    public String adminaddbook() {
        return "adminaddbook";
    }

    @RequestMapping("/admindelebook")
    public String admindelebook() {
        return "admindelebook";
    }

    //管理学生页面
    @RequestMapping("/manageStu")
    public String studentManage() {
        return "studentManage";
    }

    @RequestMapping("/bookMg")
    public String bookManage() {
        return "bookManage";
    }



//删除
    @RequestMapping("/deleteBk")
    public String delete(Integer id){
        bookService.deleteAllByid(Integer.valueOf(id));
        return "redirect:/bookInquireA";
    }

    /**
     * 查询
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/bookInquire")
   public String books(Book book, Model model,HttpSession session,@RequestParam(defaultValue = "1",value = "num") Integer ou,@RequestParam(defaultValue = "10",value = "num1")Integer ye) {
        List<Book> all = bookDao.findAll();
        List<Book> bookn=bookDao.findAllByBookname(book.getBookname());
        List<Book> pub = bookDao.findAllByPublishing(book.getPublishing());
        List<Book> leix = bookDao.findAllByLeixing(book.getLeixing());
        List<Book> publeix = bookDao.findAllByPublishingAndLeixing(book.getPublishing(), book.getLeixing());
        List<Book> publeixbook = bookDao.findAllByPublishingAndLeixingAndBookname(book.getPublishing(), book.getLeixing(),book.getBookname());

        List<Book> plx=bookDao.querypubleix(book.getPublishing(), book.getLeixing());
        session.setAttribute("ub",all);


         if (plx.size()>0){
            model.addAttribute("book", plx);
            return "bookManage";
        }
         else if (book.getPublishing() != "") {
            model.addAttribute("book", pub);
            return "bookManage";
        } else if (book.getLeixing() != "") {
            model.addAttribute("book", leix);
            return "bookManage";
        } else if (book.getBookname() != "" ) {
            model.addAttribute("book",bookn);
            return "bookManage";
        }
        else {

            model.addAttribute("book", all);
            return "bookManage";
        }
    }
    //查询所有书本
   @RequestMapping("/bookInquireA")
    public String booksup(Book book, Model model,Integer id) {
        List<Book> all = bookDao.findAll();


       List<Book> updateo = bookDao.updateo();
       List<Book> updatetwo = bookDao.updatetwo();
       List<Book> updatethree = bookDao.updatethree();
       List<Book> updatefour = bookDao.updatefour();
       model.addAttribute("book",updateo);
       model.addAttribute("two",updatetwo);
       model.addAttribute("three",updatethree);
       model.addAttribute("four",updatefour);

        return  "bookManage";
    }
    /**
     *
     * @return
     */
    @RequestMapping("/studentM")
    public String studentM(){
        return "redirect:/regulateStu";
    }
    //管理学生++
    @RequestMapping("regulateStu")
    public String findAll(User user,Model model){
        List<User> all=userDao.findAll();
        model.addAttribute("reuser",all);
        return "studentManage";
    }
    //删除学生++
    @RequestMapping("/del")
    public String deleteAllById(Integer id,Model model){
        System.out.println("123");
        userService.deleteAllById(id);
        return "studentManage";
    }
    /**
     * 修改书本
     */
    @RequestMapping("/updateBook")
    public String updateBook(Integer id,Model model,HttpSession session){
        Book allById = bookDao.findAllById(id);
        model.addAttribute("books",allById);
        return "recomposeBook";
    }
    /**
     *
     * @param book
     * @return
     */
    @RequestMapping("/adminupdate")
    public  String adminupdate(Book book){
        System.out.println(book);
        bookDao.update(book.getBookname(),book.getWriter(),book.getIntro(),book.getPublishing(),book.getIsbn(),book.getMoney(),book.getSum(),book.getLoan(),book.getCover(),book.getLeixing(),book.getId());
        return "redirect:/bookInquireA";
    }
    @RequestMapping("toAdmin")
    public String toUser(){
        return "redirect/bookInquireA";
    }
/**
 * 修改用户信息
 */
@RequestMapping("/toup")
public String toup() {
    return "/studentManage";
}
@RequestMapping("/updatestu")
    public String updatestu(Integer id, Model model){
    User userById = userDao.findUserById(id);
    model.addAttribute("user",userById);
    return "/updateStu";
}
@RequestMapping("/toupdate")
    public  String toupdate(User user){
    System.out.println(user);
    userDao.update(user.getUsername(),user.getPassword(),user.getSex(),user.getSid(),user.getAdministrator(), user.getId());
    return "redirect:/toup";
     }
    /**
     * 管理员查看借书还书
     */
    @RequestMapping("/seeBorr")
    public String seeBorr(Model model ,HttpSession session){
        ArrayList<Jieshu> bySid = (ArrayList<Jieshu>) jie.findAll();

        List<Jieshu> selectjie = jie.selectjie();
        List<Jieshu> selectjietwo = jie.selectjietwo();
        List<Jieshu> selectjiethree = jie.selectjiethree();
        model.addAttribute("borrow",selectjie);
        model.addAttribute("two",selectjietwo);
        model.addAttribute("three",selectjiethree);

//        System.out.println("32"+bySid);
//        model.addAttribute("borrow",bySid);
        return "seeBorrow";
    }

}