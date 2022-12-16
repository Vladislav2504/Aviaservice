package com.example.aviaservice.web.controller;

import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.repository.FlightRepository;
import com.example.aviaservice.repository.UserRepository;
import com.example.aviaservice.service.FlightService;
import com.example.aviaservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/flight")
public class FlightController {

    private final UserService userService;
    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public FlightController(UserService userService, FlightService flightService, FlightRepository flightRepository, UserRepository userRepository) {
        this.userService = userService;
        this.flightService = flightService;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> findFlightById (@PathVariable Long flightId) {
        flightService.findFlightById(flightId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allFlights")
    public ResponseEntity<Flight> findAllFlights () {
        flightRepository.findAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/flight/{flightName}")
//    public ResponseEntity<Optional<Flight>> findByFlightName (@PathVariable("flightName") String flightName) {
//        Optional<Flight> flight = flightService.findFlightByName(flightName);
//        return ResponseEntity.ok(flight);
//    }


}
