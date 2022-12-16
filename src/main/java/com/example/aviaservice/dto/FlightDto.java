package com.example.aviaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    @NotBlank(message = "Field cant be empty!")
    private String flightName;

    private int seats;

    private Date flightDate;

    private Long flightId;

}
