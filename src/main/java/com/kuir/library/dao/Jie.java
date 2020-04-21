package com.kuir.library.dao;

import com.kuir.library.bean.Jieshu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public interface Jie extends JpaRepository<Jieshu,Integer> {
    Jieshu findAllById(Integer id);
    ArrayList<Jieshu> findBySid(Integer sid);
    ArrayList<Jieshu> findAllBySid(Integer sid);
    //还书
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Jieshu set loan=loan-1 where id=?1")
    void updatedown(Integer id);
    @Transactional
    void deleteAllById(Integer id);

    //查看被借图书
    //0-10
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM jieshu LIMIT 0,10", nativeQuery=true)
    List<Jieshu> selectjie();
    //10-20
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM jieshu LIMIT 10,20", nativeQuery=true)
    List<Jieshu>  selectjietwo();
    //20-30
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * FROM jieshu LIMIT 20,30", nativeQuery=true)
    List<Jieshu>  selectjiethree();


}
