package se.ecutb.loffe.relational_mapping.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    @Column(unique = true, length = 6)
    private String regNumber;

    private String brand;
    private String model;
    private LocalDate regDate;

    @ManyToOne()
    private AppUser owner;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
            )
    @JoinTable(
            name = "car_statuses",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id")
    )
    private Collection<Status> statusCodes = new ArrayList<>();


    public Car(int carId, String regNumber, String brand, String model, LocalDate regDate, AppUser owner) {
        this.carId = carId;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
        this.owner = owner;
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate, AppUser owner) {
        this(0, regNumber, brand, model, regDate, owner);
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate) {
        this(0, regNumber, brand, model, regDate, null);
    }

    Car(){}

    public int getCarId() {
        return carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Collection<Status> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Collection<Status> statusCodes) {
        this.statusCodes = statusCodes;
    }

    public void addStatus(Status status) {
        statusCodes.add(status);
        status.getCars().add(this);
    }
    public void removeStatus(Status status) {
        status.getCars().remove(this);
        statusCodes.remove(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                Objects.equals(regNumber, car.regNumber) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(regDate, car.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNumber, brand, model, regDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                '}';
    }
}
