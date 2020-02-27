package se.ecutb.loffe.relational_mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.loffe.relational_mapping.entity.Address;
import se.ecutb.loffe.relational_mapping.entity.AppUser;
import se.ecutb.loffe.relational_mapping.entity.Car;
import se.ecutb.loffe.relational_mapping.entity.Status;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CommandLine implements CommandLineRunner {

    private EntityManager em;

    @Autowired
    public CommandLine(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        AppUser user1 = new AppUser("Loffe", "loffe@gmail.com", "1234");
        Address address1 = new Address("Ekvägen 15", "355 71", "Ingelstad");
        Car car1 = new Car("NAN666", "Volvo", "Amazon", LocalDate.parse("1966-01-01"));
        Car car2 = new Car("AAA111", "Volvo", "V90", LocalDate.parse("2020-01-01"));
        Status status1 = new Status("Körförbud");
        Status status2 = new Status("OK");
        Status status3 = new Status("Max 350 km/h");

        Collection<Status> statuses1 = new ArrayList<>();
        Collection<Status> statuses2 = new ArrayList<>();

        statuses1.add(status1);
        statuses2.add(status2);
        statuses2.add(status3);

        car1.setStatusCodes(statuses1);
        car2.setStatusCodes(statuses2);

        Collection<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        user1.setAddress(address1);
        user1.setOwnedCars(cars);
        user1.addCar(car1);

        em.persist(status1);
        em.persist(status2);
        em.persist(status3);
        em.persist(car1);
        em.persist(car2);
        em.persist(user1);


        System.out.println(user1.getOwnedCars());
        System.out.println(car1.getOwner());
        System.out.println(car2.getStatusCodes());



    }
}
