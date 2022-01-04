package Member_Management.Manage.service;


import Member_Management.Manage.entity.Member;
import Member_Management.Manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원정보 작성 및 저장
    public void write(Member member){
        memberRepository.save(member);
    }

    // 회원정보 리스트 불러오기
    public List<Member> memberList(){
        return memberRepository.findAll();
    }

    // 특정 회원정보 불러오기
    public Member findById(Long id){
        return memberRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void delete(Long id){
        memberRepository.delete(memberRepository.findById(id).get());
    }
}
