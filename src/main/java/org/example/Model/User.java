package org.example.Model;

import java.time.LocalDate;

public class User {
    public String Id;
    public String Dni;
    public String FirstName;
    public String LastName;
    public String Email;
    public LocalDate CreateDate;

    public User() {
        this.CreateDate = LocalDate.now();
    }
}
