package Member_Management.Manage.controller;

import Member_Management.Manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원리스트 출력
    @GetMapping("/")
    public String memberList(Model model){
        model.addAttribute("list", memberService.memberList());
        return "memberlist";
    }
}
