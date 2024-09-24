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
    @Column(name = "nameRoute")
    private String nameRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    public Route(Long id, String nameRoute, Route route) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.route = route;
    }

    public Route() {
    }

}
