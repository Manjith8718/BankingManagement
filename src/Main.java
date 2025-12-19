import Services.ManagerService;
import Services.UserService;
import java.util.Scanner;
public class Main{
    public static void main(String[] args)
    {
       System.out.println("Welcome TO BankingSystem Console App");
       Scanner sc = new Scanner(System.in);
       System.out.println("1.User");
       System.out.println("2.Manager");
       System.out.print("Your Option Please: ");
       int option = sc.nextInt();
       System.out.println();
       switch(option)
       {
           case 1: UserService.userMenu();
                   break;
           case 2: ManagerService.managerMenu();
                   break;
           default: System.out.println("Enter Valid Option");
       }
    }
}