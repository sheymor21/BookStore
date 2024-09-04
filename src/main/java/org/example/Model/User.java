package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    public String Id;
    public String Dni;
    public String FirstName;
    public String LastName;
    public String Email;
    public LocalDate CreatedAt;

    public User() {
        this.Id = UUID.randomUUID().toString();
        this.CreatedAt = LocalDate.now();
    }
}
