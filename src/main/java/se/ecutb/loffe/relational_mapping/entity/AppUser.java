package se.ecutb.loffe.relational_mapping.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Address address;

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Collection<Car> ownedCars = new ArrayList<>();

    public AppUser(int userId, String email, String name, String password, Address address, Collection<Car> ownedCars) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.ownedCars = ownedCars;
    }

    public AppUser(String email, String name, String password, Address address, Collection<Car> ownedCars) {
        this(0, email, name, password, address, ownedCars);
    }

    public AppUser(String email, String name, String password) {
        this(0, email, name, password, null, null);
    }

    public AppUser(String email, String name, String password, Collection<Car> ownedCars) {
        this(0, email, name, password, null, ownedCars);
    }


    AppUser(){}

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Collection<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    public void addCar(Car car) {
        ownedCars.add(car);
        car.setOwner(this);
    }

    public void removeCar(Car car) {
        car.setOwner(null);
        ownedCars.remove(car);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return userId == appUser.userId &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(name, appUser.name) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(address, appUser.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, name, password, address);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
}
