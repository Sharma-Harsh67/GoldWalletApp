package com.wallet.app.model;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressJagat {

    @Id
    @Column(name = "address_id")
    private Integer addressId;

    private String street;
    private String city;
    private String state;

    @Column(name = "postal_code")
    private String postalCode;
    private String country;

    public AddressJagat() {}

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}