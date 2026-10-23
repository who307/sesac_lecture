package com.who307.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.who307.common.JDBCTemplate.close;
import static com.who307.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try(FileInputStream queryStream = new FileInputStream("src/main/java/com/who307/mapper/menu-query.xml")){
            prop.loadFromXML(queryStream);
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, "봉골레파스타");
            pstmt.setInt(2,16000);
            pstmt.setInt(3, 6);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate();  // DML 작업을 하면 DML 작업이 일어난 행(row)의 갯수를 반환(int)

            pstmt = con.prepareStatement(query);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
            System.out.println("result : "+ result);
        }
    }
}
