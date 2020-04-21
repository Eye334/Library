package com.kuir.library.dao;

import com.kuir.library.bean.Book;
import com.kuir.library.bean.Jieshu;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
@Transactional
public interface BookDao extends JpaRepository<Book,Integer> {

    List<Book> findAll();
    List<Book>  findAllByPublishingOrLeixing(String publishing,String leixing);

    List<Book> findAllByPublishing(String Publishing);
    List<Book> findAllByLeixing(String leixing);
    List<Book> findAllByPublishingAndLeixing(String publishing,String leixing);
    List<Book> findAllByPublishingAndLeixingAndBookname(String publishing,String leixing,String bookname);
    List<Book> findAllByBookname(String bookname);
    Book findAllById(Integer id);
    void deleteAllById(Integer id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Book u set u.bookname=?1,u.writer=?2,u.intro=?3,u.publishing=?4,u.isbn=?5,u.money=?6,u.sum=?7,u.loan=?8,u.cover=?9,u.leixing=?10 where u.id=?11")
    void update( String bookname, String writer, String intro,String publishing,String isbn, Integer money,Integer sum,Integer loan,String cover,String leixing, Integer id);
   //借书
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Book t set t.loan=t.loan+1,t.sum=t.sum-1 where id=?1")
    void updateloan(Integer id);
    //还书
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Book t set  t.loan=t.loan-1,t.sum=t.sum+1  where id=?1")
    void updatedown(Integer id);
    //分页
//    Page<Book> findAll(Specification<Book> spec, Pageable pageable);

    //多条件查询

    @Query(value = "select * from book  where publishing = ?1 and leixing=?2", nativeQuery=true)
    List<Book> querypubleix(String publishing,String leixing);
 @Query(value = "select * from book  where publishing = ?1 and leixing=?2 and bookname=?3", nativeQuery=true)
    List<Book> queryplb(String publishing,String leixing,String bookname);


 //0-5
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM book LIMIT 0,5", nativeQuery=true)
    List<Book>  updateo();
    //5-10
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM book LIMIT 5,10", nativeQuery=true)
    List<Book>  updatetwo();
    //10-15
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM book LIMIT 10,15", nativeQuery=true)
    List<Book>  updatethree();
    //15-20
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM book LIMIT 15,20", nativeQuery=true)
    List<Book>  updatefour();

}
