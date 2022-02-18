package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static String memid1, memid2;


    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        Member one = memberRepository.save(new Member("Ib","d@b.dk","123","Poul", "Hartling",
                "Vejnavn","København","1000",20, LocalDate.of(1953,4,16)));
        memid1 = one.getUsername();
        Member two = memberRepository.save(new Member("Ib","a@s.dk","123","Poul","Hartling"
                ,"Vejnavn","København","2000",20,LocalDate.of(1953,4,16)));
        memid2 = two.getUsername();
    }

    @Test
    public void testAddMember(){
        Member member = memberRepository.save(new Member("Ib","edede@b.dk","123","Poul",
                "Hartling","Vejnavn","København","3000",20,LocalDate.of(1953,4,16)));
        assertNotEquals(null,member.getUsername());
        //assertEquals(2,memberRepository.count());
    }

    @Test
    public void testCount(){
        assertEquals(1,memberRepository.count());
    }

    @Test
    public void testFindByUsername(){
        Member mfound = memberRepository.findById(memid1).orElse(null);
        //assertEquals("d@b.dk",mfound.getEmail());

    }

    @Test
    public void testFindByZip(){
        List<Member> members = memberRepository.findMembersByZip("2000");
        assertEquals(1, members.size());
        assertEquals("2000", members.get(0).getZip());
    }

}