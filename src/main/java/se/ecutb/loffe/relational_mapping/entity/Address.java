package se.ecutb.loffe.relational_mapping.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    private String street;
    private String zipCode;
    private String city;

    public Address(int addressId, String street, String zipCode, String city) {
        this.addressId = addressId;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    Address(){}

    public Address(String street, String zipCode, String city) {
        this(0, street, zipCode, city);
    }

    public int getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId &&
                Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, zipCode, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
