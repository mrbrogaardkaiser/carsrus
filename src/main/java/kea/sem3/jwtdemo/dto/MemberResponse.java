package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private int ranking;

    private LocalDate dateOfBirth;

    public MemberResponse(Member member, boolean includeAll){
        this.username= member.getUsername();
        this.email=member.getEmail();
        this.firstName= member.getFirstName();
        this.lastName=member.getLastName();
        if(includeAll){
            this.password= member.getPassword();
            this.ranking= member.getRanking();
        }

    }

    public static List<MemberResponse> getMembersFromEntities(List<Member> members){
        return members.stream().map(member->new MemberResponse(member, true)).collect(Collectors.toList());
    }







}
