package DAO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Models.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    public static boolean createUser(User user)
    {
         String sql = "INSERT INTO users (NAME,EMAIL,PASSWORD) VALUES(?,?,?) ";
         try(Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);)
         {
             ps.setString(1,user.getName());
             ps.setString(2,user.getEmail());
             ps.setString(3,user.getPassword());
             return ps.executeUpdate() > 0;
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return false;
    }

    public static boolean validateUser(String email,String password)
    {
        String sql = "SELECT password FROM users where email=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
              String storedHash = rs.getString(1);
              return BCrypt.checkpw(password,storedHash);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean userExists(User u)
    {
        String sql = "SELECT COUNT(*) from users where email = ?";
        try(Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
          )
        {
            ps.setString(1,u.getEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1)>0;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
