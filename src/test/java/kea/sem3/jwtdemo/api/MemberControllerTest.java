package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest // gør hvad der skal til så vi får en fuld app kontekst. vi kan gøre alt det vi kunne fra postman.
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // konvertering mellem json og java

    static String username1, username2;

    @BeforeEach
    void setUp() {

        username1 = memberRepository.save(new Member("ET","d@f.dk","wec","erc","efverv","efverv",
                "erverv","werver",233, LocalDate.of(2000,4,5))).getUsername();

        username2 = memberRepository.save(new Member("dsds","k@k.dk","wec","erc","efverv","efverv",
                "erverv","werver",233, LocalDate.of(2000,4,5))).getUsername();
    }

    @Test
    void testMemberByUsername() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/members/"+username1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(username1));


    }

    @Test
    void getMember() {
    }

    @Test
    void editMember() {
    }

    @Test
    void addMember() {
    }

    @Test
    void deleteMember() {
    }
}