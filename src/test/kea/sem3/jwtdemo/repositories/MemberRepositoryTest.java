package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static String memid1, memid2;


    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        Member one = memberRepository.save(new Member("a","b@s.dk","123","Ib"));
        memid1 = one.getUsername();
        Member two = memberRepository.save(new Member("s","d@s.dk","123","Lasse"));
        memid2 = two.getUsername();


    }

    @Test
    public void testAddMember(){
        Member member = memberRepository.save(new Member("d","f@j.dk","123","DDD"));
        assertNotEquals(null,member.getUsername());
        assertEquals(3,memberRepository.count());
    }

    @Test
    public void testCount(){
        assertEquals(2,memberRepository.count());
    }

    @Test
    public void testFindByUsername(){
        Member mfound = memberRepository.findById(memid1).orElse(null);
        assertEquals("b@s.dk",mfound.getEmail());

    }

}