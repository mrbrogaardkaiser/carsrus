package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ReservationResponse {
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime reservationDate;
    private LocalDate rentalDate;
    private CarResponse carResponse;
    private MemberResponse memberResponse;

    public ReservationResponse(Reservation reservation, boolean includeAll){
        this.id=reservation.getId();
        this.reservationDate=reservation.getReservationDate();
        this.rentalDate=reservation.getRentalDate();

    }

}
