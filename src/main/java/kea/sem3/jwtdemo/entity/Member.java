package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.MemberRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser{

    @Column(length = 30)
    String firstName;

    @Column(length = 50)
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private int ranking;

    private LocalDate datOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private Set<Reservation> reservations = new HashSet<>();


    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, String zip, int ranking, LocalDate datOfBirth) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.ranking = ranking;
        this.datOfBirth = datOfBirth;
    }

    public Member(MemberRequest body){
        this.setUsername(body.getUsername());
        this.setEmail(body.getEmail());
        this.setPassword(body.getPassword());
        this.setFirstName(body.getFirstName());
        this.setLastName(body.getLastName());
        this.setCity(body.getCity());
        this.setDatOfBirth(body.getDateOfBirth());
        this.setStreet(body.getStreet());
        this.setZip(body.getZip());
        this.setRanking(body.getRanking());

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addReservation(Reservation reservation){
        reservation.setMember(this);
        reservations.add(reservation);
    }


}
