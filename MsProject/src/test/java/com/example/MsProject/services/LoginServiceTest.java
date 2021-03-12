package com.example.MsProject.services;

import com.example.MsProject.auth.Dao.LoginDao;
import com.example.MsProject.auth.Model.User;
import com.example.MsProject.auth.Service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginServiceTest {

    @Mock
    LoginDao loginDao;

    @InjectMocks
    LoginService loginService;

    User user1;
    User user2;

    @BeforeEach
    void init(){
        user1=new User();
        user1.setUserId(1);
        user1.setFirstName("jatin");
        user1.setLastName("kumar");
        user1.setEmailId("email1");
        user1.setLocation("delhi");

        user2=new User();
        user2.setUserId(2);
        user2.setFirstName("komal");
        user2.setLastName("gupta");
        user2.setLocation("delhi");
        user2.setEmailId("email2");
    }

    @Test
    public void loginByGoogleTest() throws Exception{
        when(loginDao.loginByGoogle(user1)).thenReturn(user1);

        User returnedUser=loginService.loginByGoogle(user1);
        assertEquals(user1,returnedUser);
    }

    @Test
    public void getAllUserTest() throws Exception
    {
        when(loginDao.getAllUser()).thenReturn(Arrays.asList(user1,user2));

        List<User> allUsers=loginService.getAllUser();
        assertEquals(Arrays.asList(user1,user2),allUsers);
    }

}
