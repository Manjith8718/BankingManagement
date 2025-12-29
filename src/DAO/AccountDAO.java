package DAO;

import Models.Account;
import Utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
     public static boolean createAccount(Account a)
     {
         String sql = "INSERT INTO accounts(user_id,account_number,pin,account_type) VALUES(?,?,?,?)";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setInt(1,a.getUserId());
             ps.setLong(2,a.getAccountNumber());
             ps.setString(3,a.getPin());
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

     public static boolean createPin(long AccountNumber,String pin)
     {
         String sql = "UPDATE accounts SET pin = ? where account_number = ?";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setLong(2,AccountNumber);
             ps.setString(1,pin);
             int rows = ps.executeUpdate();
             return rows > 0;
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

     public static boolean validatePin(long AccountNumber,String pin)
     {
         String sql = "SELECT pin from accounts where account_number = ?";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setLong(1,AccountNumber);
             ResultSet rs = ps.executeQuery();
             if(rs.next()) {
                 return BCrypt.checkpw(pin, rs.getString("pin"));
             }
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

     public static boolean validAccount(long AccountNumber)
     {
         String sql = "SELECT count(*) from accounts where account_number = ?";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps  = conn.prepareStatement(sql);)
         {
             ps.setLong(1,AccountNumber);
             ResultSet rs = ps.executeQuery();
             return rs.getInt(1) > 0;
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

     public static boolean creditAmount(Connection con,long accountNumber,int amount)
     {
         String sql = "update accounts set balance = balance + ? where account_number = ?";
         try(PreparedStatement ps = con.prepareStatement(sql);)
         {
              ps.setInt(1,amount);
              ps.setLong(2,accountNumber);
              return ps.executeUpdate() > 0;
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

    public static boolean debitAmount(Connection con,long accountNumber,int amount)
    {
        String sql = "update accounts set balance = balance - ? where account_number = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setInt(1,amount);
            ps.setLong(2,accountNumber);
            return ps.executeUpdate() > 0;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean accountStatus(long accountNumber)
    {
        String sql = "select status from accounts where account_number = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setLong(1,accountNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return "ACTIVE".equals(rs.getString("status"));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
