package DAO;

import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinDAO {
     public static boolean wrongPin(long accountNumber)
     {
          String sql = "select account_id from accounts where account_number = ?";
          try(Connection conn = DBConnection.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql);)
          {
              ps.setLong(1,accountNumber);
              ResultSet rs = ps.executeQuery();
              if(rs.next())
              {
                  return checkPin(rs.getInt(1));
              }
          }
          catch(SQLException e)
          {
              System.out.println(e.getMessage());
          }
          return false;
     }

     public static boolean checkPin(int accountId)
     {
         String sql = "select account_id from pin_lock where account_id = ?";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);)
         {
             ps.setInt(1, accountId);
             ResultSet rs = ps.executeQuery();
             if(rs.next())
             {
                return insertPin(accountId);
             }
             else
             {
                return updatePin(accountId);
             }
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

     public static boolean insertPin(int accountId)
     {
         String sql = "insert into pin_lock values(?)";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);)
         {
             ps.setInt(1, accountId);
             if(ps.executeUpdate()>0)
             {
                 return true;
             }
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         return false;
     }

    public static boolean updatePin(int accountId)
    {
        String sql = "update pin_lock set attempts = attempts + 1 where account_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, accountId);
            if(ps.executeUpdate()>0)
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
