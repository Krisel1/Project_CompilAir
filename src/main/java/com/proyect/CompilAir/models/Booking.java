package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

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
    private int phone;

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

    @Column(name="numberOfPlaces")
    private int numberOfPlaces;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("booking-user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    public Flight getFlight(){
        return flight;
    }
    public void setFlight(Flight flight){
        this.flight = flight;
    }


    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

<<<<<<< HEAD
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
=======
    public int getNumberOfPlaces() {
        return numberOfPlaces;
>>>>>>> 60cf1726df8233f6bb1c25819ef3c517a48971a4
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }
    public Booking(){

    }

    public Booking(Long id, String name, String surname, int phone, String genre, String email, LocalDate birthdayDate, String identificationType, String identificationNumber, String address, int zipCode, String country, String city, User user,Route route,int numberOfPlaces,Flight flight) {
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
        this.numberOfPlaces = numberOfPlaces;
        this.flight = flight;
    }


}

