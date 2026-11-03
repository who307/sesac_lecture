package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// @Entity는 이 클래스를 JPA가 관리할 대상으로 등록, @Table은 연결할 DB 테이블을 지정
@Entity
@Table(name="tbl_member")
public class Member {

    // @Id는 기본 키 필드이고, IDENTITY 방식에서는 INSERT할 때 DB가 회원 번호를 생성(auto-increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private int memberNo;
    @Column(name="member_id", nullable = false, unique = true, length = 50)
    private String memberId;
    @Column(name="member_pwd", nullable = false, length = 100)
    private String memberPwd;
    @Column(name="member_name", nullable = false, length = 50)
    private String memberName;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="enroll_date", nullable = false)
    private LocalDateTime enrollDate;

    // enum순서가 바뀌어도 DB 값의 의미가 유지되도록 이름으로 저장
    @Enumerated(EnumType.STRING)
    @Column(name="member_role", nullable = false)
    private MemberRole memberRole;
    @Column(name="status", nullable = false, length = 1)
    private String status;

    // JPA가 조회 결과로 엔티티를 만들 때 사용할 기본 생성자
    protected Member() {}

    // 회원을 새로 만들 때 필요한 생성자
    public Member(
            String memberId, String memberPwd, String memberName,
            String phone, String address, LocalDateTime enrollDate,
            MemberRole memberRole, String status
    ) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }
}
