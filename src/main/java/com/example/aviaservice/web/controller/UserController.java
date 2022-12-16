package com.example.aviaservice.web.controller;

import com.example.aviaservice.dto.AuthDto;
import com.example.aviaservice.dto.FlightDto;
import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.entity.User;
import com.example.aviaservice.exception.UserNotFoundException;
import com.example.aviaservice.repository.FlightRepository;
import com.example.aviaservice.repository.UserRepository;
import com.example.aviaservice.service.FlightService;
import com.example.aviaservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public UserController(UserService userService, FlightService flightService, FlightRepository flightRepository, UserRepository userRepository) {
        this.userService = userService;
        this.flightService = flightService;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) {
        if (user == null) {
            throw new UserNotFoundException("Please enter valid value");
        }
        User u = new User();
        return new ResponseEntity<User>(HttpStatus.OK);

    }

    @PostMapping("/authentication")
    public ResponseEntity<String> login(@RequestBody AuthDto authDTO) {
        if (userService.exists(authDTO.getEmail(), authDTO.getPassword())) {
            String s = authDTO.getEmail();
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addFlight")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDto flightDto) {

        // Сделать проверку на доступ (роль ADMIN, USER)
        Flight flight = flightService.composeFlightInfo(flightDto);
        Flight save = flightService.save(flight);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
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

