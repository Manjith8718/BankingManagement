package Services;

import DAO.AccountDAO;
import Models.Account;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountService {
    public static void accountMenu()
    {
        System.out.println("Welcome TO Banking Page");
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Create Bank Account");
        System.out.println("2.Credit Money");
        System.out.println("3.Debit Money");
        System.out.println("4.Transfer Money To Other Account");
        System.out.println("5.Create Pin");
        System.out.print("Your Option Please: ");
        int option = sc.nextInt();
        System.out.println();
        switch(option)
        {
            case 1: createAccount(sc);
                break;
            case 2: creditMoney(sc);
                break;
            case 3: debitMoney(sc);
                break;
            case 4: transferMoney(sc);
                break;
            case 5 : changePin(sc);
                break;
            default:System.out.println("Enter Valid Option");
        }
    }
    public static void createAccount(Scanner sc)
    {
          Account a = accountDetails(sc);
          String sql = "INSERT INTO accounts(user_id,account_number,pin,account_type) VALUES(?,?,?,?)";
          if(AccountDAO.createAccount(sql,a))
          {
              System.out.println("Account Created Successfully");
          }
          else
          {
              System.out.println("Failed to Create Account");
          }
    }
    public static void creditMoney(Scanner sc)
    {

    }

    public static void debitMoney(Scanner sc)
    {

    }

    public static void transferMoney(Scanner sc)
    {

    }

    public static void changePin(Scanner sc)
    {
        System.out.println("New Pin Set Up");
        System.out.print("Please Enter Account Number: ");
        Long accountNumber = sc.nextLong();
        System.out.print("Please Enter New Pin : ");
        int pin = sc.nextInt();
        String sql = "UPDATE TABLE accounts SET pin = ? where account_number = ?";
        if(AccountDAO.createPin(sql,accountNumber,pin))
        {
            System.out.println("Pin Updated Successfully");
        }
        else
        {
            System.out.println("Failed to Update Pin");
        }
    }
    public static Account accountDetails(Scanner sc)
    {
        System.out.print("Please Enter User Id: ");
        int  userId = sc.nextInt();
        System.out.print("Please Enter Account Number: ");
        Long accountNumber = sc.nextLong();
        System.out.println("Please Enter Pin:");
        int pin = sc.nextInt();
        System.out.println("Please Enter Type Of Account");
        String type = sc.next();
        return new Account(userId,accountNumber,pin,type);
    }

    public static Account moneyDetails()
    {

    }
}