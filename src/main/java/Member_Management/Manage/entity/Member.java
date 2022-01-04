package Member_Management.Manage.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id를 pk로 생성

    private String grade; // 회원등급

    private String name; // 이름

    private String type; // 등록종목

    private String start_day; // 신청날짜

    private String end_day; // 종료날짜

    private int d_day; // 잔여일자

    private int price; // 가격

    private String phone_number; // 핸드폰번호

    private String address; // 주소
}



