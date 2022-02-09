package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    String username;
    String firstName;
    String lastName;
    String email;

    public MemberDto(Member m){
        this.username = m.getUsername();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.email = m.getEmail();
    }

    public static List<MemberDto> getList(List<Member> memberEntities){
        return memberEntities.stream().map(MemberDto::new).collect(Collectors.toList());
    }

}
