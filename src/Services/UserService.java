package Services;
import DAO.ManagerDAO;
import DAO.UserDAO;
import Models.Manager;
import Models.User;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
public class UserService {
     public static void userMenu()
     {
         System.out.println("Welcome TO User Page");
         Scanner sc = new Scanner(System.in);
         System.out.println("1.Login");
         System.out.println("2.Register");
         System.out.print("Your Option Please: ");
         int option = sc.nextInt();
         System.out.println();
         switch(option)
         {
             case 1: userLogin(sc);
                     break;
             case 2: userRegister(sc);
                     break;
             default:System.out.println("Enter Valid Option");
         }
     }
     public static void userLogin(Scanner sc)
     {
         System.out.println("User Login Details Please");
         User u = loginDetails(sc);
         if(UserDAO.validateUser(u.getEmail(),u.getPassword()))
         {
             System.out.println("User Login Successful");
         }
         else
         {
             System.out.println("Invalid Email or Password");
         }
     }

     public static void userRegister(Scanner sc)
     {
         System.out.println("User Registration Details Please");
         User u = registerDetails(sc);
         if(UserDAO.userExists(u))
         {
             System.out.println("User Already Exists Please Log In");
             userLogin(sc);
             return;
         }
         else {
             UserDAO.createUser(u);
             System.out.println("User Registered Successfully......");
         }

     }
    public static User loginDetails(Scanner sc)
    {
        System.out.print("Please Enter Email: ");
        String email = sc.next();
        System.out.print("Please Enter Password: ");
        String password = sc.next();
        return new User(null, email,password);
    }
    public static User registerDetails(Scanner sc)
    {
        String email;
        String password;
        String name;
        System.out.print("Please Enter Name: ");
        name = sc.nextLine();
        System.out.print("Please Enter Email: ");
        email = sc.next();
        System.out.println();
        System.out.print("Please Enter Password: ");
        password = sc.next();
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println();
        return new User(name,email,hashed);
    }
}
