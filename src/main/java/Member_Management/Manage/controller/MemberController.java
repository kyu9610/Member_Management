package Member_Management.Manage.controller;

import Member_Management.Manage.entity.Member;
import Member_Management.Manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원리스트 출력
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<Member> member = memberService.memberList();
        model.addAttribute("list", member);
        return "memberlist";
    }

    // 회원정보 추가
    @GetMapping("/member/write")
    public String memberWriteForm(){
        return "memberwrite";
    }

    // 회원정보 추가 처리
    @PostMapping("/member/writing")
    public String memberWriting(Member member, Model model){
        memberService.write(member);

        return "redirect:/member/list";
    }
}
