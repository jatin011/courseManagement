package com.example.MsProject.auth.Service;

import com.example.MsProject.auth.Dao.LoginDao;
import com.example.MsProject.auth.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;
//
//    public Boolean loginByRemote(User user)
//    {
//        return loginDao.loginByUserName(user);
//    }

    public User loginByGoogle(User user) throws  Exception
    {
        return loginDao.loginByGoogle(user);
    }

    public List<User> getAllUser(){
        return loginDao.getAllUser();
    }

}
