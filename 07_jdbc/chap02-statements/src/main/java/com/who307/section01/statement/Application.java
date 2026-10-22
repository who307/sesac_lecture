package com.who307.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.who307.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Statement stmt = null;
        ResultSet rset = null;

        Connection con = getConnection();
        System.out.println(con);

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT MENU_NAME, MENU_PRICE FROM TBL_MENU");

            while(rset.next()) {
                System.out.println(rset.getString("MENU_NAME") + ", "
                        + rset.getInt("MENU_PRICE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}