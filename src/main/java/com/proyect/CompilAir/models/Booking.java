package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name ="genre")
    private String genre;

    @Column(name ="email")
    private String email;

    @Column(name ="birthdayDate")
    private LocalDate birthdayDate;

    @Column(name = "identificationType")
    private String identificationType;

    @Column(name ="identificationNumber")
    private String identificationNumber;

    @Column(name ="address")
    private String address;

    @Column(name ="zipCode")
    private int zipCode;

    @Column(name ="country")
    private String country;

    @Column(name ="city")
    private String city;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("booking-user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;


    public Booking(){

    }

    public Booking(Long id, String name, String surname, String phone, String genre, String email, LocalDate birthdayDate, String identificationType, String identificationNumber, String address, int zipCode, String country, String city, User user,Route route) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.genre = genre;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.user = user;
        this.route = route;
    }

}

