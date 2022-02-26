package kea.sem3.jwtdemo.api;


import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public ReservationController(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    @PostMapping("/{id}")
    Reservation getReservation(@PathVariable int id){
        return reservationRepository.getById(id);
    }

    @PostMapping
    Reservation createReservation(@RequestBody Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @PutMapping("/{reservationId}/cars/{carId}")
    Reservation addCarToReservation(
            @PathVariable int reservationId,
            @PathVariable int carId
    ) {
        Reservation reservation = reservationRepository.getById(reservationId);
        Car car = carRepository.findById(carId).get();
        reservation.setCar(car);
        return reservationRepository.save(reservation);
    }

    @PutMapping("/{reservationId}/members/{username}")
    Reservation addMemberToReservation(
            @PathVariable int reservationId,
            @PathVariable String username
    ){
      Reservation reservation = reservationRepository.getById(reservationId);
      Member member = memberRepository.findMemberByUsername(username);
      reservation.setMember(member);
      return reservationRepository.save(reservation);
    }
}
