package com.example.aviaservice.service.maper;

import com.example.aviaservice.dto.FlightDto;
import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public Flight convertFlightDto(FlightDto flightDto) {
        return new Flight(flightDto.getFlightName(), flightDto.getSeats(), flightDto.getFlightDate());
    }

}
