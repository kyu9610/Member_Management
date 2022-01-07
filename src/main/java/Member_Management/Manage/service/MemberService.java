package Member_Management.Manage.service;


import Member_Management.Manage.entity.Member;
import Member_Management.Manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원정보 작성 및 저장
    public void write(Member member){
        // 회원의 신청날짜 , 종료날짜 , 등급별가격 계산
        member.setStart_day(LocalDate.now());
        member.setEnd_day(member.getStart_day().plusDays(member.getD_day()));
        member = setGradePrice(member);
        memberRepository.save(member);
    }

    // 회원정보 리스트 불러오기
    public List<Member> memberList(){
        List<Member> members = memberRepository.findAll();
        setDay(members); // 잔여일자 계산하기
        return members;
    }

    // 특정 회원정보 불러오기
    public Member findById(Long id){
        return memberRepository.findById(id).get();
    }

    // 특정 회원정보 삭제
    public void delete(Long id){
        memberRepository.delete(memberRepository.findById(id).get());
    }

    // 특정 회원정보 수정
    public void update(Member ex_member, Member member){

        member.setStart_day(ex_member.getStart_day());
        member.setEnd_day(ex_member.getStart_day().plusDays(member.getD_day()));
        member = setGradePrice(member);

        memberRepository.save(member); // 저장
    }


    // 회원 기간연장 추가
    public void extension(int plusDate){
        List<Member> members = memberRepository.findAll();
        for(Member member : members){
            int day; // 잔여일자 변수
            day = member.getD_day() + plusDate;
            member.setD_day(day);
            member.setEnd_day(member.getStart_day().plusDays(member.getD_day()));

            memberRepository.save(member);
        }
    }


    // 회원리스트 잔여일자 관리
    public List<Member> setDay(List<Member> members){
        for(Member member : members){
            LocalDateTime now = LocalDate.now().atStartOfDay(); // LocalDate to LocalDateTime 오늘날짜
            LocalDateTime date = member.getEnd_day().atStartOfDay(); // LocalDate to LocalDateTime 종료날짜

            int day; // 잔여일자 계산 변수
            day = Long.valueOf(Duration.between(now,date).toDays()).intValue(); // 두 날짜의 차이 계산후 Integer Type으로
            member.setD_day(day); // 잔여일자 저장
        }
        return members;
    }


    // 회원리스트 등급별 관리
    public Member setGradePrice(Member member){
        String memberGrade = member.getGrade(); // 멤버의 등급을 받아오는 변수 생성
        double discount = 0; // 할인가격

        /**
         * 할인율 셋팅
         * 브론즈    0%
         * 실버     5%
         * 골드     10%
         * 플래티넘  15%
         * 다이아   20%
         */

        // switch 문을 사용하여 등급별로 금액을 관리.
        switch(memberGrade) {
            case "브론즈":
                discount = member.getPrice(); // 브론즈이면 그대로
                break;
            case "실버":
                discount = member.getPrice() * 0.95;
                break;
            case "골드":
                discount = member.getPrice() * 0.9;
                break;
            case "플래티넘":
                discount = member.getPrice() * 0.85;
                break;
            case "다이아":
                discount = member.getPrice() * 0.8;
                break;
        }
        member.setPrice((int)discount); // 등급별로 가격설정

        return member;
    }
}
