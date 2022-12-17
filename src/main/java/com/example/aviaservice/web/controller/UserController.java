package com.example.aviaservice.web.controller;

import com.example.aviaservice.dto.AuthDto;
import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.entity.User;
import com.example.aviaservice.repository.FlightRepository;
import com.example.aviaservice.repository.UserRepository;
import com.example.aviaservice.service.FlightService;
import com.example.aviaservice.service.UserService;
import com.example.aviaservice.service.maper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final FlightService flightService;
    private  final UserMapper userMapper;

    public UserController(UserService userService, FlightService flightService, UserMapper userMapper) {
        this.userService = userService;
        this.flightService = flightService;
        this.userMapper = userMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthDto> registration(@RequestBody AuthDto authDto, User user) {
        userService.emailExists(user.getEmail());
        userService.registration(userMapper.convertAuthDtoToUser(authDto));
        return new ResponseEntity<>(authDto, HttpStatus.CREATED);

    }

    @PostMapping("/authentication")
    public ResponseEntity<String> login(@RequestBody AuthDto authDTO) {
        if (userService.exists(authDTO.getEmail(), authDTO.getPassword())) {
            String s = authDTO.getEmail();
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<AuthDto> update(@PathVariable("userId") Long userId, @RequestBody AuthDto authDto) {
        authDto.setUserId(userId);
        AuthDto user = userService.update(authDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{flightId}/flightBooking")
    public ResponseEntity<Flight> flightBooking(HttpServletRequest request, @PathVariable("flightId") Long flightId) {
        User user = (User) request.getAttribute("user");
        flightService.flightBooking(user, flightId);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{flightId}/removeFlightBooking")
    public ResponseEntity<Flight> removeFlightBooking(HttpServletRequest request, @PathVariable("flightId") Long flightId) {
        User user = (User) request.getAttribute("user");
        flightService.removeFlightBooking(user, flightId);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

}




}

