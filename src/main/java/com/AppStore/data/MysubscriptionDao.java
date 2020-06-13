/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AppStore.data;
import com.AppStore.domain.Application;
import com.AppStore.domain.Downloads;
import com.AppStore.utils.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMRUDDHI
 */


public class MysubscriptionDao extends Downloads{
    String sql1 = "select * from mysubscriptiondb where id=? and username=?";
    String query = "insert into mysubscriptiondb (id,username,status)" + " values (?,?,?)";
    String url = "jdbc:mysql://localhost:3306/zenithdb";
    String username = "root";
   

//    public MysubscriptionDao() {
//         Downloads mysub = new Downloads();
//    }
    public void addtomysubscription(Long id,String username, String status) throws SQLException {

        System.out.println("hello");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username, Utils.SQL_PASSWORD);

            PreparedStatement st = con.prepareStatement(query);
            st.setLong(1, id);
            st.setString(2,username);
            st.setString(3,status);
            st.execute();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
      
     
     }
    public boolean checkifsubscribed(Long id,String username) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username, Utils.SQL_PASSWORD);

             PreparedStatement st = con.prepareStatement(sql1);
            st.setLong(1,id);
            st.setString(2,username);
            ResultSet rs = st.executeQuery();
            System.out.println("In rs");
            if(rs.next()) {
//				System.out.println("In if");
                return true;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block

        }
        return false;
    }
    
   }



