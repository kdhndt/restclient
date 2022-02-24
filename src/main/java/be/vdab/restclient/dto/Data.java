package be.vdab.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//Java naamgeving conventie verbiedt _ tekens in variabelen, koppel je variable op deze manier met JSON
//avatar attribuut is niet nodig in deze applicatie, zelfde geldt voor email adres?
public record Data(long id, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName) {
}
