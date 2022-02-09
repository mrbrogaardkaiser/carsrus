package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findMembersByZip(String zip);

    Member findMemberByUsername(String username);

/*
    @Query("select (count(m)>0)from Member m where m.username= :userName")
    boolean userExist(String username);*/
}
