package DAO;

import Models.Manager;
import Utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
    public static boolean managerExists()
    {
        String sql = "SELECT COUNT(*) from manager";
        try(Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();)
        {
            if(rs.next())
            {
                 return rs.getInt(1) >0;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean createManager(Manager m)
    {
        String sql = "INSERT INTO manager (EMAIL,PASSWORD) VALUES(?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            )
        {
            ps.setString(1,m.getEmail());
            ps.setString(2,m.getPassword());
            return ps.executeUpdate()>0;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean validateManager(String email,String password)
    {
        String sql = "SELECT password FROM manager where email=?";
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
}
