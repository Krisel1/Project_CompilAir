package com.proyect.CompilAir.excepcions;

public class PastFlightDateException extends RuntimeException{
    public PastFlightDateException(String message){
        super(message);
    }

}
