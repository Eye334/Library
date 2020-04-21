package com.kuir.library.server;

import com.kuir.library.bean.User;
import com.kuir.library.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUser(String username, String password){
        return userDao.findUserByUsernameAndPassword(username,password);
    }
    public User save(User user){
      return userDao.save(user);
    }
    public void findAll(User user){
        userDao.findAll();
    }
    public void findAllById(Integer id){
        userDao.findAllById(id);
    }public void findById(Integer Sid){
        userDao.findBySid(Sid);
    }
    public User findUserUsername(String username){
     return userDao.findUserByUsername(username);
    }

    public void findAllBySid(User user){
        userDao.findAllBySid(user.getSid());
    }
    public void deleteAllBySid(Integer sid){
        userDao.deleteAllBySid(sid);
    }
    public void deleteAllById(Integer id){
        userDao.deleteAllById(id);
    }
    public User findUserById(Integer id){
       return userDao.findUserById(id);
    }
}
