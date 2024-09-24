package com.proyect.CompilAir.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "Flights")
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El número de vuelo no puede estar vacío")
    @Size(max = 10, message = "El número de vuelo no puede exceder 10 caracteres")
    @Column(unique = true, nullable = false)
    private String flightName;

    @NotNull
    @Column(nullable = false)
    private boolean flightStatus;

    @Future(message = "La hora de salida debe estar en el futuro")
    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Future(message = "La hora de llegada debe estar en el futuro")
    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Min(value = 1, message = "El número total de asientos debe ser al menos 1")
    @Column(nullable = false)
    private long totalSeats;

    public Flight(int id, String flightName, boolean flightStatus, LocalDateTime departureDate, LocalDateTime returnDate, int totalSeats) {
        this.id = id;
        this.flightName = flightName;
        this.flightStatus = flightStatus;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalSeats = totalSeats;
    }

    public Flight() {

    }

//    @OneToMany
 //   @JoinColumn(name = "route_id", nullable = false)
//    private Route route;

 //   @OneToMany
 //   @JoinColumn(name = "booking_id", nullable = false)
  //  private Booking booking;



}
