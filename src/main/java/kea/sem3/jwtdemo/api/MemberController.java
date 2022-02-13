package kea.sem3.jwtdemo.api;



import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {


    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

    @GetMapping("/{username}")
    public MemberResponse getMember(@PathVariable String username) throws Exception{
        return memberService.getMember(username,false);
    }

    @PutMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username) throws Exception{
        return memberService.editMember(body, username);
    }

    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    @DeleteMapping("/{username}")
    public void deleteMember(@PathVariable String username) throws Exception{
        memberService.deleteMember(username);
    }
}
