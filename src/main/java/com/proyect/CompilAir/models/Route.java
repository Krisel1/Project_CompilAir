package com.proyect.CompilAir.models;

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
    @Getter
    @Column(name = "nameRoute")
    private String nameRoute;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Route() {
    }

    public Route(String nameRoute, Long id, Booking booking) {
        this.nameRoute = nameRoute;
        this.id = id;
        this.booking = booking;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

