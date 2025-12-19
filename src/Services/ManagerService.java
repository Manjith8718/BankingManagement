package Services;
import DAO.ManagerDAO;
import Models.Manager;
import Models.User;
import Utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class ManagerService {
    public static void managerMenu()
    {
        System.out.println("Welcome TO Manager Page");
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.print("Your Option Please: ");
        int option = sc.nextInt();
        System.out.println();
        switch(option)
        {
            case 1: managerLogin(sc);
                    break;
            case 2:
                    if(!ManagerDAO.managerExists()) {
                       managerRegister(sc);
                    }
                    else
                    {
                        System.out.println("Manager Position Filled");
                    }
                    break;
            default:System.out.println("Enter Valid Option");
        }
    }
    public static void managerLogin(Scanner sc)
    {
        System.out.println("Manager Login Details Please");
        Manager m = loginDetails(sc);
        if(ManagerDAO.validateManager(m.getEmail(),m.getPassword()))
        {
            System.out.println("Manager Login Successful");
        }
        else
        {
            System.out.println("Invalid Email or Password");
        }
    }
    public static void managerRegister(Scanner sc)
    {
        System.out.println("Manager Registration Details Please");
        Manager m = managerDetails(sc);
        ManagerDAO.createManager(m);
        System.out.println("Manager Registered Successfully......");
    }
    public static Manager managerDetails(Scanner sc)
    {
        String email;
        String password;
        System.out.print("Please Enter Email: ");
        email = sc.next();
        System.out.println();
        System.out.print("Please Enter Password: ");
        password = sc.next();
        System.out.println();
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return new Manager(email,hashed);
    }
    public static Manager loginDetails(Scanner sc)
    {
        System.out.print("Please Enter Email: ");
        String email = sc.next();
        System.out.print("Please Enter Password: ");
        String password = sc.next();
        return new Manager(email,password);
    }
}
