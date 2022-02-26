package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.ReservationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    private LocalDate rentalDate;


    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @ManyToOne
    private Car car;


    @JoinColumn(name = "member_username", referencedColumnName = "username")
    @ManyToOne
    private Member member;

    public Reservation(LocalDate rentalDate, Car car, Member member){
        this.rentalDate=rentalDate;
        this.car= car;
        this.member=member;
        car.addReservation(this);
        member.addReservation(this);
    }

    public Reservation(ReservationRequest body){
        this.id= body.getId();
        this.rentalDate= body.getRentalDate();
        this.car = body.getCar();
        this.member=body.getMember();
    }


    public void setCar(Car car){
        this.car= car;
    }

    public void setMember(Member member){
        this.member=member;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", rentalDate=" + rentalDate +
                ", car=" + car +
                ", member=" + member +
                '}';
    }
}
