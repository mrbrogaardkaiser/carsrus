package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest // anvende h2 in memory db. github actions kan køre vores test uden at have adgang til vores "rigtige" db
class CarServiceInMemoryTest {

    @Autowired
    CarRepository carRepository;

    CarService carService;

    static int car1Id, car2Id;

    @BeforeAll
    static void setup(@Autowired CarRepository carRepository){

        car1Id = carRepository.save(new Car("Volvo", "C40", 560,10)).getId();
        car2Id = carRepository.save(new Car("WW", "Up", 300,10)).getId();
    }

    @BeforeEach
    void setupService(){
        carService = new CarService(carRepository);

    }

    @Test
    void getCars() {
        List<CarResponse> carResponses = carService.getCars();
        assertEquals(2,carResponses.size());
        assertInstanceOf(CarResponse.class,carResponses.get(0));
        assertThat(carResponses, containsInAnyOrder(hasProperty("model", is("C40")), hasProperty("model", is("Up"))));
    }

    @Test
    void getCar() {
    }

    @Test
    void addCar() {
    }

    @Test
    void deleteCar() {
    }

}