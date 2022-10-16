package server;

public class User {
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public int getRoles() {
        return Roles;
    }

    public void setRoles(int roles) {
        Roles = roles;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public User(String email, int roles, String date, String password, String name, String family, int office, int active) {
        Email = email;
        Roles = roles;
        Date = date;
        Password = password;
        Name = name;
        Family = family;
        Office = office;
        Active = active;


    }

    private  String Email;
    private  String Password;
    private String Name;
    private String Family;

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    private int Active;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public int getOffice() {
        return Office;
    }

    public void setOffice(int office) {
        Office = office;
    }

    private int Office;



    private int Roles;
    private String Date;

    public User() {

    }
}
