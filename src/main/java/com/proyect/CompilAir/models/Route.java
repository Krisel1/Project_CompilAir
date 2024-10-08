package com.proyect.CompilAir.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nameRoute")
    private String nameRoute;


    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    public Route() {
    }
    public Route(Long id, String nameRoute, Set<Flight> flights, Set<Booking> bookings) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.flights = flights;
        this.bookings = bookings;
    }

    public Route(String nameRoute, long id) {
        this.nameRoute = nameRoute;
        this.id = id;
        this.flights = new HashSet<>();
        this.bookings = new HashSet<>();
    }

}

