package com.kuir.library.server;

import com.kuir.library.bean.Book;



import com.kuir.library.dao.BookDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookService {

    @Autowired
    BookDao bookDao;
//    @Autowired
//    BkDao bkDao;


    public Book getIsbn(Book book){
        return bookDao.save(book);

    }
    public void save(Book book){
       bookDao.save(book);
       bookDao.findAll();
    }
    public void deleteAllByid(Integer id){
        bookDao.deleteAllById(id);
    }

//更新
//    public void updateUsernameById(Integer id,String bookname){
//        bkDao.updateUsernameById(id,bookname);
//    }
    public void findbook(Book book){
        bookDao.findAllById(book.getId());
        bookDao.findAll();
        bookDao.findAllByPublishing(book.getPublishing());
        bookDao.findAllByLeixing(book.getLeixing());
        bookDao.findAllByPublishingAndLeixing(book.getPublishing(),book.getLeixing());
        bookDao.findAllByBookname(book.getBookname());

        bookDao.findAllByPublishingOrLeixing(book.getPublishing(), book.getLeixing());

    }


}
