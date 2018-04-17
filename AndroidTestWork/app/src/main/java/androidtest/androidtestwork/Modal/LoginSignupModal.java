package androidtest.androidtestwork.Modal;

/**
 * Created by Aru on 24-03-2018.
 */

public class LoginSignupModal {
    private String UserName;
    private String City;
    private String Email;
    private String Contact;
    private String Password;
    private int id;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
