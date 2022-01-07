package Member_Management.Manage.controller;

import Member_Management.Manage.entity.Member;
import Member_Management.Manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // 특정 회원정보 출력
    @GetMapping("/member/view")
    public String memberView(Long id, Model model){
        model.addAttribute("member",memberService.findById(id));

        return "memberview";
    }

    // 특정 회원정보 수정
    @GetMapping("/member/modify/{id}")
    public String memberModify(@PathVariable("id") Long id, Model model){
        model.addAttribute("member",memberService.findById(id));

        return "membermodify";
    }

    // 특정 회원정보 수정처리
    @PostMapping("/member/update/{id}")
    public String memberUpdate(@PathVariable("id") Long id, Member member){
        Member ex_member = memberService.findById(id);
        memberService.update(ex_member,member);
        return "redirect:/member/list";
    }

    // 특정 회원정보 삭제
    @GetMapping("/member/delete")
    public String memberDelete(Long id){
        memberService.delete(id);
        return "redirect:/member/list";
    }
}
