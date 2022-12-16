package com.example.aviaservice.service.maper;

import com.example.aviaservice.dto.AuthDto;

import com.example.aviaservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertAuthDto(AuthDto authDto) {
        return new User(authDto.getFirstName(), authDto.getLastName(), authDto.getPassword(), authDto.getEmail());
    }

}
