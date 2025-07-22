package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expense {
    //@JsonProperty("id") from previous code, not needed here as we are using Lombok's @Data annotation


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int expenseType; // 0 for income, 1 for expense

    private String date; // Date in "yyyy-MM-dd" format


    private double amount; // Amount of the expense or income


    private String category; // Category of the expense or income


    private String account; // Account associated with the expense or income


    private String note; // Additional notes or description of the expense or income

    @ManyToOne(fetch = FetchType.LAZY)//user data loaded only when needed, also helps with performance
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to AppUser
    @JsonIgnore//prevent circular reference during serialization
    private AppUser user; // User associated with the expense or income

}
