package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
