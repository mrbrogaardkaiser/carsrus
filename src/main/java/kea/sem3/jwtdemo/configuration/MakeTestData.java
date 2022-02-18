package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Controller
@Profile("!test") // kører ikke når der testes
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public  void makePlainUsers(){

        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);


        Member member1 = new Member("Ib","a@b.dk","123","Poul","Hartling","Vejnavn","København","1662",20, LocalDate.of(1953,4,16));
        member1.addRole(Role.USER);
        userRepository.save(member1);

        Member member2 = new Member("Svend","b@b.dk","123","Poul","Schluter","Vejnavn","København","1662",20, LocalDate.of(1953,4,16));
        member2.addRole(Role.USER);
        userRepository.save(member2);

        Member member3 = new Member("Poul","c@b.dk","123","Poul","Nyrup","Vejnavn","København","1662",20, LocalDate.of(1953,4,16));
        member3.addRole(Role.USER);
        userRepository.save(member3);

        Member member4 = new Member("Mads","d@b.dk","123","Poul","Madsen","Vejnavn","København","1662",20, LocalDate.of(1953,4,16));
        member4.addRole(Role.USER);
        userRepository.save(member4);

        Car car = new Car("VW","Golf",1000,200);
        Car car1 = new Car("Volvo","V40",1000,200);
        Car car2 = new Car("Suzuki","Alto",1000,200);
        carRepository.save(car);
        carRepository.save(car2);
        carRepository.save(car1);


        Reservation r1 = new Reservation(LocalDate.of(2022,3,4),car,member1);

        System.out.println(car.getReservations().size());
        System.out.println(r1);

        reservationRepository.save(r1);




/*
        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(car.getId(),LocalDate.of(2022,4,5));

        if(res==null){
            //Reservation r2 = new Reservation(LocalDate.of(2022,4,5),car,member3);
            //reservationRepository.save(r2);


        }
        else
            System.out.println("Car is booked");

*/

        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.deleteAll();
        makePlainUsers();

    }
}
