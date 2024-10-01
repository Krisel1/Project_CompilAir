package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

    public Route() {
    }

    public Route(String nameRoute, Long id, Flight flight) {
        this.nameRoute = nameRoute;
        this.id = id;
        this.flight = flight;
    }


}

