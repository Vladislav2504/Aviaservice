package com.example.aviaservice.service.maper;

import com.example.aviaservice.dto.AuthDto;

import com.example.aviaservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User convertAuthDtoToUser(AuthDto authDto) {
        User user = new User();
        user.setFirstName(authDto.getFirstName());
        user.setLastName(authDto.getLastName());
        user.setPassword(authDto.getPassword());
        user.setEmail(authDto.getEmail());
        return user;
    }


}
