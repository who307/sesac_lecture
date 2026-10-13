package com.who307.section02.superkeyword;

// Product 클래스를 상속받는다
public class SmartPhone extends Product{

    // 스마트폰만의 통신사(agency) 필드를 추가
    private String agency;

    public SmartPhone(){
        super();
        System.out.println("SmartPhone 기본 생성자 호출");
    }

    // 부모 필드 포함 모든 필드를 초기화 하는 생성자
    public SmartPhone(String code, String name, int price, String agency) {
        super(code, name, price);
        this.agency = agency;
        System.out.println("SmartPhone 클래스의 부모 필드도 초기화 하는 생성자 호출");

    }

    // getInformation() 메소드 오버라이딩

    @Override
    public String getInformation() {
        String parentInfo = super.getInformation();
        String smartPhoneInfo = " " + agency;
        return parentInfo + smartPhoneInfo;
    }
}
