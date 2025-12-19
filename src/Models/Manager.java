package Models;
public class Manager {
     private int managerId;
     private String email;
     private String password;

     public Manager() {}

     public Manager(int managerId,String email,String password)
     {
        this.managerId = managerId;
        this.email = email;
        this.password = password;
     }

    public Manager(String email,String password)
    {

        this.email = email;
        this.password = password;
    }
     public void setId(int managerId)
     {
        this.managerId = managerId;
     }
     public void setEmail(String email)
     {
        this.email = email;
     }
     public void setPassword(String password)
     {
        this.password = password;
     }
     public int getId()
     {
        return managerId;
     }
     public String getEmail()
     {
        return email;
     }
     public String getPassword()
     {
        return password;
     }
}
