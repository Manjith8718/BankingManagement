package Services;

import DAO.AccountDAO;
import Models.Account;
import Utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
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
          if(AccountDAO.createAccount(a))
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
        System.out.println("welcome user to credit page");
        System.out.print("Please Enter Account Number: ");
        long accountNumber = sc.nextLong();
        if(!AccountDAO.validAccount(accountNumber))
        {
            System.out.println("AccountNumber Does not Exist Please Recheck It");
            return;
        }

        System.out.print("Please Enter Required Amount To Credit");
        int creditamount = sc.nextInt();
        if(creditamount<=0)
        {
            System.out.println("Amount must be greater than zero");
            return;
        }

        System.out.print("Please Enter Your 4 Digit Pin Number : ");
        String pin = sc.next();
        if(pin.matches("\\d{4}"))
        {
            System.out.print("Wrong Format Pin Should Be Exactly 4 Digits");
            return;
        }
        if(!AccountDAO.validatePin(accountNumber,pin))
        {
            System.out.print("Wrong Pin Entered, Please Recheck");
            return;
        }
        if(AccountDAO.creditAmount(accountNumber,creditamount))
        {
            System.out.printf("%d Amount Credited Successfully From Your Account",creditamount);
        }
        else
        {
            System.out.print("Failed To Credit From Your Account");
        }
    }

    public static void debitMoney(Scanner sc)
    {
        System.out.println("welcome to user to debit money");
        System.out.print("Please Enter Account Number: ");
        long accountNumber = sc.nextLong();
        if(!AccountDAO.validAccount(accountNumber))
        {
            System.out.println("AccountNumber Does not Exist Please Recheck It");
            return;
        }

        System.out.print("Please Enter Required Amount To Debit");
        int debitamount = sc.nextInt();
        if(debitamount<=0)
        {
            System.out.println("Amount must be greater than zero");
            return;
        }

        System.out.print("Please Enter Your 4 Digit Pin Number : ");
        String pin = sc.next();
        if(!pin.matches("\\d{4}"))
        {
            System.out.print("Wrong Format Pin Should Be Exactly 4 Digits");
            return;
        }
        if(!AccountDAO.validatePin(accountNumber,pin))
        {
            System.out.print("Wrong Pin Entered, Please Recheck");
            return;
        }
        if(AccountDAO.debitAmount(accountNumber,debitamount))
        {
            System.out.printf("%d Amount Debited Successfully From Your Account",debitamount);
        }
        else
        {
            System.out.print("Failed To Credit From Your Account");
        }
    }

    public static void transferMoney(Scanner sc)
    {
        System.out.println("welcome to user to transfer money to other account");
        System.out.print("Please Enter Account Number: ");
        long accountNumber = sc.nextLong();
        if(!AccountDAO.validAccount(accountNumber))
        {
            System.out.println("Your AccountNumber Does not Exist Please Recheck It");
            return;
        }
        System.out.print("Please Enter Receiver Account Number: ");
        long receiverNumber = sc.nextLong();
        if(!AccountDAO.validAccount(accountNumber))
        {
            System.out.println("Your ReceiverNumber Does not Exist Please Recheck It");
            return;
        }
        System.out.print("Please Enter Your 4 Digit Pin Number : ");
        String pin = sc.next();
        if(!pin.matches("\\d{4}"))
        {
            System.out.print("Wrong Format Pin Should Be Exactly 4 Digits");
            return;
        }
        if(!AccountDAO.validatePin(accountNumber,pin))
        {
            System.out.print("Wrong Pin Entered, Please Recheck");
            return;
        }
        System.out.print("Please Enter  Amount To Transfer");
        int transferamount = sc.nextInt();
        if(transferamount<=0)
        {
            System.out.println("Amount must be greater than zero");
            return;
        }
        if(AccountDAO.debitAmount(receiverNumber,transferamount) && AccountDAO.creditAmount(accountNumber,transferamount))
        {
            System.out.println("Transfer is Success");
        }
        else
        {
            System.out.println("Transfer is Fail");
        }
    }

    public static void changePin(Scanner sc)
    {
        System.out.println("New Pin Set Up");
        System.out.print("Please Enter Account Number: ");
        long accountNumber = sc.nextLong();
        if(!AccountDAO.validAccount(accountNumber))
        {
            System.out.println("AccountNumber Does not Exist Please Recheck It");
        }
        System.out.print("Enter Old PIN: ");
        String oldPin = sc.next();

        if (!AccountDAO.validatePin(accountNumber, oldPin)) {
            System.out.println("Incorrect old PIN");
            return;
        }

        System.out.print("Enter New PIN: ");
        String newPin = sc.next();

        if (!newPin.matches("\\d{4}")) {
            System.out.println("PIN must be exactly 4 digits");
            return;
        }


        String hashedPin = BCrypt.hashpw(newPin, BCrypt.gensalt());
        if(AccountDAO.createPin(accountNumber,hashedPin))
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
        long accountNumber = sc.nextLong();
        System.out.print("Please Enter Pin:");
        String pin = sc.next();
        String hashedPin = BCrypt.hashpw(pin, BCrypt.gensalt());
        System.out.print("Please Enter Type Of Account");
        String type = sc.next();
        return new Account(userId,accountNumber,hashedPin,type);
    }

}