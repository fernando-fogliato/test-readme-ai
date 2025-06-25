package com.example.springbootcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street address is required")
    @Size(min = 5, max = 200, message = "Street address must be between 5 and 200 characters")
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    @Column(name = "city", nullable = false)
    private String city;

    @Size(max = 100, message = "State must be less than 100 characters")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    @Column(name = "country", nullable = false)
    private String country;

    @Pattern(regexp = "^[0-9A-Z\\s-]{3,20}$", message = "Postal code format is invalid")
    @Column(name = "postal_code")
    private String postalCode;

    @Size(max = 50, message = "Address type must be less than 50 characters")
    @Column(name = "address_type")
    private String addressType; // HOME, WORK, BILLING, SHIPPING, etc.

    @Size(max = 200, message = "Additional info must be less than 200 characters")
    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "active")
    private Boolean active = true;

    // Default constructor
    public Address() {
    }

    // Constructor with parameters
    public Address(String street, String city, String state, String country, String postalCode,
                  String addressType, String additionalInfo, Double latitude, Double longitude,
                  Boolean isPrimary, Boolean active) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.addressType = addressType;
        this.additionalInfo = additionalInfo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isPrimary = isPrimary;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", addressType='" + addressType + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isPrimary=" + isPrimary +
                ", active=" + active +
                '}';
    }
} 