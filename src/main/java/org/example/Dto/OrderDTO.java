package org.example.Dto;

import java.time.LocalDate;

public class OrderDTO {

    private String Id;
    public String UserName;
    public String BookTitle;
    public String BookAuthor;
    public Double BookPrice;
    public LocalDate PurchaseDate;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

