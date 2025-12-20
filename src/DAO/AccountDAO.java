package DAO;

import Models.Account;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {
     public static boolean createAccount(String sql, Account a)
     {
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setInt(1,a.getUserId());
             ps.setLong(2,a.getAccountNumber());
             ps.setInt(3,a.getPin());
             ps.setString(4,a.getAccountType());
             int rows = ps.executeUpdate();
             return rows > 0;
         }
         catch(SQLException e)
         {
             if(e.getMessage().contains("Duplicate")) {
                 System.out.println("Account already exists");
             }
         }
         return false;
     }

     public static boolean createPin(String sql,long AccountNumber,int pin)
     {
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setLong(1,AccountNumber);
             ps.setInt(2,pin);
             int rows = ps.executeUpdate();
             return rows > 0;
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }
}
