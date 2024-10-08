package com.proyect.CompilAir.dto.booking;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "The surname cannot be null")
    private String surname;

    private int phone;

    private String genre;

    @Email(message = "The email must be valid")
    private String email;

    private LocalDate birthdayDate;

    private String identificationType;

    private String identificationNumber;

    private String address;

    private int zipCode;


    @NotBlank(message = "The country cannot be empty")
    @Size(min = 2, max = 56, message = "The country must be between 2 and 56 characters")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "The country must only contain letters")
    private String country;

    @NotBlank(message = "The city cannot be empty")
    @Size(min = 2, max = 100, message = "The city must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "The city should only contain letters")
    private String city;

    private Long route_id;

    private Long user_id;

    public BookingDTO(Long id, Long user_id, String name, String surname, int phone, String email, LocalDate birthdayDate, String identificationNumber, String genre, String identificationType, String address, int zipCode, String country, String city, Long route_id) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.identificationNumber = identificationNumber;
        this.genre = genre;
        this.identificationType = identificationType;
        this.address = address;
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.route_id = route_id;
    }

    public Long getRouteId() { return route_id; }
    public void setRouteId(Long route_id) { this.route_id = route_id; }

    public Long getUserId() { return user_id; }
    public void setUserId(Long user_id) { this.user_id = user_id; }

    public BookingDTO() {
    }
}
