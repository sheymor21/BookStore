package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Order {

    public String Id;
    public String UserId;
    public String BookId;
    public LocalDate CreatedAt;

    public Order() {
        this.Id = UUID.randomUUID().toString();
        this.CreatedAt = LocalDate.now();
    }
}
