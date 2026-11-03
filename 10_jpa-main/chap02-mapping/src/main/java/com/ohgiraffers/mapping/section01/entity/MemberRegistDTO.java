package com.ohgiraffers.mapping.section01.entity;

import java.time.LocalDateTime;

public class MemberRegistDTO {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String phone;
    private String address;
    private LocalDateTime enrollDate;
    private MemberRole memberRole;
    private String status;

    public MemberRegistDTO() {}

    public MemberRegistDTO(
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

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getEnrollDate() {
        return enrollDate;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public String getStatus() {
        return status;
    }
}
