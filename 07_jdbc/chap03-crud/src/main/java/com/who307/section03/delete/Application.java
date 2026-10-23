package com.who307.section03.delete;

import com.who307.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.who307.common.JDBCTemplate.close;
import static com.who307.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 메뉴 번호를 입력하세요 : ");
        int menuCode = sc.nextInt();

        MenuDTO deleteMenu = new MenuDTO();
        deleteMenu.setCode(menuCode);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try(FileInputStream queryStream = new FileInputStream("src/main/java/com/who307/mapper/menu-query.xml")){
            prop.loadFromXML(queryStream);
            String query = prop.getProperty("deleteMenu");

            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, deleteMenu.getCode());

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
            System.out.println("메뉴 삭제 성공!");
        } else {
            System.out.println("메뉴 삭제 실패!");
        }
    }
}
