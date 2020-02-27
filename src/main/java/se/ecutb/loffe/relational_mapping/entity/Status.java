package se.ecutb.loffe.relational_mapping.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    private String statusCode;

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "car_statuses",
            joinColumns = @JoinColumn(name = "status_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Collection<Car> cars = new ArrayList<>();

    public Status(int statusId, String statusCode, Collection<Car> cars) {
        this.statusId = statusId;
        this.statusCode = statusCode;
        this.cars = cars;
    }

    public Status(String statusCode, Collection<Car> cars) {
        this(0, statusCode, cars);
    }

    public Status(String statusCode) {
        this(0, statusCode, null);
    }

    Status(){}

    public int getStatusId() {
        return statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        car.getStatusCodes().add(this);
    }
    public void removeCar(Car car) {
        car.getStatusCodes().remove(this);
        cars.remove(car);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId &&
                Objects.equals(statusCode, status.statusCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusCode);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
