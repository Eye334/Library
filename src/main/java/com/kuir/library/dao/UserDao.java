package com.kuir.library.dao;

import com.kuir.library.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
@Transactional
public interface UserDao extends JpaRepository<User,Integer> {
    User findUserByUsernameAndPassword(String username,String password);

    User findAllByUsernameAndPassword(String username,String password);
    User findAllByUsername(String username);
    List<User> findAll();
    List<User>findBySidAndUsername(Integer sid,String username);
    List<User> findAllById(Integer id);
    User findBySid(Integer sid);
    User findAllBySid(Integer sid);
    void deleteAllBySid(Integer sid);
    void deleteAllById(Integer id);
User findUserById(Integer id);
    User findUserByUsername(String username);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User u set u.username=?1,u.password=?2,u.sex=?3,u.sid=?4,u.administrator=?5 where u.id=?6")
    void update( String username, String password, String sex, Integer sid,Integer administrator, Integer id);
//    Page<User> findAll(Specification<User> spec, Pageable pageable);

    //修改密码
    @Modifying(clearAutomatically = true)
    @Transactional
//    @Query("update User set password=?1 where  sid=?2")
    @Query("update User a set a.password=?1 where  a.sid=?2")
    void uddatepassword(String password,Integer sid);
}
