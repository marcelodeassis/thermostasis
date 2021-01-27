package com.marcelodeassis.thermostasis.domain;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_call_log")
public class ApiCallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "country")
    private String country;

    @Column(name = "created_on")
    private LocalDateTime createdOn; //LocalDateTime.of(2020, 5, 1, 12, 30, 0)



}
