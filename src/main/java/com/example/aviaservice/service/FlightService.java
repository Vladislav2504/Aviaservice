package com.example.aviaservice.service;

import com.example.aviaservice.dto.FlightDto;
import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.entity.User;
import com.example.aviaservice.exception.FlightNotFoundException;
import com.example.aviaservice.repository.FlightRepository;
import com.example.aviaservice.service.maper.FlightMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Transactional
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public Flight save(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    public Flight composeFlightInfo(FlightDto flightDto) {
        Flight flight = flightMapper.convertFlightDto(flightDto);
        return flight;
    }

    public Optional<Flight> findFlightById(Long id) {
        Optional<Flight> flightById = flightRepository.findById(id);
        if (flightById.isPresent()) {
            return flightById;
        } else {
            throw new FlightNotFoundException();
        }
    }

    public Optional<Flight> findFlightByName(String flightName)  throws FlightNotFoundException {
        return flightRepository.findByFlightName(flightName);
    }

    public Flight flightBooking(User user, Long postId) {
        Optional<Flight> flightById = flightRepository.findById(postId);
        if (flightById.isPresent()) {
            Flight flight = flightById.get();
            user.getMyFlights().add(flight);
            return flight;
        } else {
            throw new FlightNotFoundException();
        }
    }

    public Flight removeFlightBooking(User user, Long postId) {
        Optional<Flight> flightById = flightRepository.findById(postId);
        if (flightById.isPresent()) {
            Flight flight = flightById.get();
            user.getMyFlights().remove(flight);
            return flight;
        } else {
            throw new FlightNotFoundException();
        }
    }

}
