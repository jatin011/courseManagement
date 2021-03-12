package com.example.MsProject.Controller;

import com.example.MsProject.auth.Model.User;
import com.example.MsProject.auth.Service.LoginService;
import com.example.MsProject.auth.controller.LoginController;
import com.example.MsProject.courses.Services.CourseServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    CourseServices courseServices;

    @MockBean
    LoginService loginService;

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
    public void getAllUserTest() throws Exception {

        lenient().when(this.loginService.getAllUser()).thenReturn(allUser);
        mockMvc.perform(get("/login/getAll")).andDo(print()).
                andExpect(status().isOk());


    }


    @Test
    public void loginByGoogleTest() throws Exception {
        //assuming user alreadyexist
        when(this.loginService.loginByGoogle(user1)).thenReturn(user1);

        String jsonString=objectMapper.writeValueAsString(user1);

        mockMvc.perform(post("/login/googleLogin").contentType(MediaType.APPLICATION_JSON).content(jsonString)
        ).andDo(print()).andExpect(status().isOk());
    }



}
