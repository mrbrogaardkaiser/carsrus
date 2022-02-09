package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

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

    public Member(MemberDto body){
        this.setUsername(body.getUsername());
        this.setFirstName(body.getFirstName());
        this.setLastName(body.getLastName());
        this.setEmail(body.getEmail());
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
