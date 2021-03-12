package com.example.MsProject.auth.Dao;

import com.example.MsProject.auth.Mapper.MapRowToUser;
import com.example.MsProject.auth.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.lang.Exception;
import java.util.List;

@Repository
public class LoginDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(LoginDao.class);



    public boolean doesUserExist(String emailId) throws Exception
    {
        try{
            String finalQuery= "select count(*) from users where emailId='"+emailId+"'";
            boolean doesExist=jdbcTemplate.queryForObject(finalQuery,Integer.class) > 0;
            logger.info("User Exist Query Worked");
            return doesExist;
        }
        catch(Exception e)
        {
            logger.error(e.getCause()+"in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public User loginByGoogle(User user) throws  Exception
    {
        try
        {
            boolean alreadyExist=doesUserExist(user.getEmailId());
            if(alreadyExist)
            {
                return jdbcTemplate.queryForObject("select firstName,lastName,emailId,userId,location from users where emailId='"+user.getEmailId()+"'",MapRowToUser.userRowMapperLambda);
            }
            else
            {
                final String add_new_user="insert into users (firstName,lastName,emailId,joiningDate,location) values( ? , ? , ? , ? , ? )";
                KeyHolder keyHolder = new GeneratedKeyHolder();
                java.sql.Date date = new Date(System.currentTimeMillis());
                this.jdbcTemplate.update(connection -> {
                    PreparedStatement stmt = connection.prepareStatement(add_new_user);
                    stmt.setString(1, user.getFirstName());
                    stmt.setString(2, user.getLastName());
                    stmt.setString(3,user.getEmailId());
                    stmt.setDate(4,date );
                    stmt.setString(5,"delhi");
                    return stmt;
                });
            }
            logger.info("New User Created with emailId="+user.getEmailId());
            User newUser= jdbcTemplate.queryForObject("select firstName,lastName,emailId,userId from users where emailId='"+user.getEmailId()+"'",MapRowToUser.userRowMapperLambda);
            logger.info("Successfully retrieved the newly created user");
            return newUser;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+"in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }

    public List<User> getAllUser()
    {
        List<User> users=this.jdbcTemplate.query("select * from users",MapRowToUser.userRowMapperLambda);
        return users;
    }

}
