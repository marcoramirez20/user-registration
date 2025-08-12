package com.example.users.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public class PhoneRequest {

    @NotBlank
    private String number;

    @NotBlank
    private String citycode;

    @NotBlank
    @JsonAlias({"contrycode"})
    private String countrycode;

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCitycode() { return citycode; }
    public void setCitycode(String citycode) { this.citycode = citycode; }

    public String getCountrycode() { return countrycode; }
    public void setCountrycode(String countrycode) { this.countrycode = countrycode; }
}
