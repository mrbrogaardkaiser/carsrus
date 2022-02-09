package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberDto;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository= memberRepository;
    }

    public MemberDto getMember(String username) throws Exception{
        Member member = memberRepository.findMemberByUsername(username);
        return new MemberDto(member);
    }

    public List<MemberDto> getMembers(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberDto::new).collect(Collectors.toList());
    }

    public MemberDto addMember(MemberDto body){
        Member newMember = memberRepository.save(new Member(body));
        return new MemberDto(newMember);
    }

    public void deleteCustomer(String username) throws Exception{
        if(!memberRepository.existsById(username)){
            throw  new Exception("Not found");
        }
        memberRepository.deleteById(username);
    }
}
