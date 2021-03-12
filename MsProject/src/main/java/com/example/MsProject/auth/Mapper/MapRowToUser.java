package com.example.MsProject.auth.Mapper;

import com.example.MsProject.auth.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapRowToUser  {
    private MapRowToUser(){}

    public static final RowMapper<User> userRowMapperLambda = ((resultSet, i) ->  {
        User user= new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setEmailId(resultSet.getString("emailId"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setLocation(resultSet.getString("location"));
        return  user;
    });

}
