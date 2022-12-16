package com.example.aviaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight extends AbstractEntity{

    // Место отправление и место прибытия. прим. MinskToMoscow
    private String flightName;

    private int seats;

    private Date flightDate;






}
