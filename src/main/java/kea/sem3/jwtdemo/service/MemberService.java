package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository= memberRepository;
    }

    public MemberResponse getMember(String username, boolean includeAll) throws Exception{
        Member member = memberRepository.findMemberByUsername(username);
        return new MemberResponse(member,includeAll);
    }

    public List<MemberResponse> getMembers(){
        List<Member> members = memberRepository.findAll();
        return MemberResponse.getMembersFromEntities(members);
    }

    public MemberResponse addMember(MemberRequest body){
        Member newMember = memberRepository.save(new Member(body));
        return new MemberResponse(newMember,false);
    }

    public void deleteMember(String username) throws Exception{
        if(!memberRepository.existsById(username)){
            throw new Exception("Not found");
        }
        memberRepository.deleteById(username);
    }
}
