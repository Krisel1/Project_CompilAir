package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nameRoute")
    private String nameRoute;

    @ManyToOne
    @JoinColumn(name = "fight_id", nullable = false)
    private Flight flight;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Booking> bookings;

    public Route() {
    }

    public Route(String nameRoute, Long id, Flight flight) {
        this.nameRoute = nameRoute;
        this.id = id;
        this.flight = flight;
    }


}

