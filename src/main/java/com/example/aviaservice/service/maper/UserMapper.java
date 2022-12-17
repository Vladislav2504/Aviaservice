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

    public AuthDto convertAuthDtoToAuthDto(AuthDto authDto, AuthDto savedAuthDto) {
        savedAuthDto.setFirstName(authDto.getFirstName());
        savedAuthDto.setLastName(authDto.getLastName());
        savedAuthDto.setPassword(authDto.getPassword());
        return savedAuthDto;
    }

    public AuthDto convertAuthDtoToAuthDto(User user) {
        AuthDto authDto = new AuthDto();
        authDto.setUserId(user.getId());
        authDto.setFirstName(user.getFirstName());
        authDto.setLastName(user.getLastName());
        authDto.setPassword(user.getPassword());
        authDto.setEmail(user.getEmail());
        return authDto;
    }
}
