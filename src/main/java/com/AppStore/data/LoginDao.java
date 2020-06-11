package com.AppStore.data;
import com.AppStore.utils.Utils;

import java.sql.*;

public class LoginDao {
    String sql1 = "select * from customerData where Email=? and Password=?";
    String sql2 = "select * from customerData where Telephone=? and Password=?";
    String url = "jdbc:mysql://localhost:3306/zenithdb";
    String username = "root";

    public boolean checkDetails(String uname, String pass) throws SQLException {

        try{
            System.out.println("In dao " + uname + pass);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(sql1);
            st.setString(1,uname);
            st.setString(2,pass);
            ResultSet rs = st.executeQuery();
            System.out.println("In rs");

            PreparedStatement st1 = con.prepareStatement(sql2);
            st1.setString(1,uname);
            st1.setString(2,pass);
            ResultSet rs1 = st1.executeQuery();
            System.out.println("In rs2");

//			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//			while (rs.next()) {
//			    for (int i = 1; i <= columnsNumber; i++) {
//			        if (i > 1) System.out.print(",  ");
//			        String columnValue = rs.getString(i);
//			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
//			    }
//			    System.out.println("");
//			}

            if(rs.next()) {
//				System.out.println("In if");
                return true;
            }
            else if(rs1.next()) {
//				System.out.println("In else if");
                return true;
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
}
