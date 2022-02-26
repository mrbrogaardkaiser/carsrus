package kea.sem3.jwtdemo.service;

public class ReservationService {

    ReservationService reservationService;

    public ReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
