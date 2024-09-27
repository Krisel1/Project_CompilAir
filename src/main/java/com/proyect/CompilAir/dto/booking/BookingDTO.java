package com.proyect.CompilAir.dto.booking;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "The surname cannot be null")
    private String surname;

    @Email(message = "The email must be valid")
    private String email;

    @NotBlank(message = "The city cannot be empty")
    @Size(min = 2, max = 100, message = "The city must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "The city should only contain letters")
    private String city;

    @NotBlank(message = "The country cannot be empty")
    @Size(min = 2, max = 56, message = "The country must be between 2 and 56 characters")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "The country must only contain letters")
    private String country;


    public BookingDTO(Long id, String name, String surname, String email, String city, String country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
    }

}
