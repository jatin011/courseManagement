package com.example.MsProject.Dao;

import com.example.MsProject.MsProjectApplication;
import com.example.MsProject.auth.Dao.LoginDao;
import com.example.MsProject.auth.Mapper.MapRowToUser;
import com.example.MsProject.auth.Model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MsProjectApplication.class)
public class UserDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;


    @InjectMocks
    private  LoginDao loginDao;


    @Mock
    private DataSource ds;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @InjectMocks
     MapRowToUser mapRowToUser;


    User user1=new User();
    User user2=new User();
    List<User> allUser=new ArrayList<>();

    @BeforeEach
    void  init(){
        user1.setUserId(1);
        user1.setEmailId("email1");
        user1.setFirstName("Jatin");
        user1.setLastName("Kumar");
        user1.setLocation("Delhi");

        user2.setUserId(2);
        user2.setEmailId("email2");
        user2.setFirstName("vikash");
        user2.setLastName("Kumar");
        user2.setLocation("Delhi");
        allUser.add(user1);
        allUser.add(user2);
    }

    @Test
    public void doesUserExist() throws Exception {

        when(this.jdbcTemplate.queryForObject("select count(*) from users where emailId='email1'", Integer.class)).thenReturn(1);
        boolean doesExist= loginDao.doesUserExist("email1");
        assertEquals(true, doesExist);

    }

    @Test
    public void addUser() throws Exception {
        when(this.jdbcTemplate.queryForObject("select count(*) from users where emailId='email1'", Integer.class)).thenReturn(1);

        when(this.jdbcTemplate.queryForObject("select firstName,lastName,emailId,userId,location from users where emailId='email1'",MapRowToUser.userRowMapperLambda)).thenReturn(user1);

        User user=loginDao.loginByGoogle(user1);

        assertEquals(user1,user);


        when(this.jdbcTemplate.queryForObject("select count(*) from users where emailId='email2'", Integer.class)).thenReturn(0);


        assertNotNull(ds);
        when(c.prepareStatement("insert into users (firstName,lastName,emailId,joiningDate,location) values( ? , ? , ? , ? , ? )")).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);

        java.sql.Date date = new Date(System.currentTimeMillis());
        stmt.setString(1, user2.getFirstName());
        stmt.setString(2, user2.getLastName());
        stmt.setString(3,user2.getEmailId());
        stmt.setDate(4,date );
        stmt.setString(5,"delhi");


        when(rs.first()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(user2.getUserId());
        when(rs.getString(2)).thenReturn(user2.getFirstName());
        when(rs.getString(3)).thenReturn(user2.getLastName());
        when(stmt.executeQuery()).thenReturn(rs);

        when(jdbcTemplate.queryForObject("select firstName,lastName,emailId,userId from users where emailId='"+user2.getEmailId()+"'",MapRowToUser.userRowMapperLambda)).thenReturn(user2);

        User newUser=loginDao.loginByGoogle(user2);
        assertEquals(user2,newUser);
    }

    @Test
    public void getAllUser() throws SQLException {

        when(this.jdbcTemplate.query("select * from users",MapRowToUser.userRowMapperLambda)).thenReturn(allUser);
        List<User> allUsers=loginDao.getAllUser();
        assertEquals(allUser.size(),allUsers.size());
    }
}
