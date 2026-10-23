package com.who307.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import com.who307.model.dto.MenuDTO;

import static com.who307.common.JDBCTemplate.close;
import static com.who307.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 이름을 입력해주세요 : ");
        String menuName = sc.nextLine();
        System.out.println("메뉴 가격을 입력하세요 :");
        int menuPrice = sc.nextInt();
        System.out.println("카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.println("판매 여부를 결정해주세요 : ");
        sc.nextLine();
        String orderableStatusl = sc.nextLine().toUpperCase();

        MenuDTO newMenu = new MenuDTO();
        newMenu.setName(menuName);
        newMenu.setPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatusl);

        Properties prop = new Properties();

        try(FileInputStream queryStream = new FileInputStream("src/main/java/com/who307/mapper/menu-query.xml")){
            prop.loadFromXML(queryStream);
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, newMenu.getName());
            pstmt.setInt(2, newMenu.getPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();  // DML 작업을 하면 DML 작업이 일어난 행(row)의 갯수를 반환(int)

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }
        if(result > 0){
            System.out.println("메뉴 등록 성공!");
        } else {
            System.out.println("메뉴 등록 실패!");
        }
    }
}
