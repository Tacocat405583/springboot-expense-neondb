package com.example.utils;

import com.example.model.Expense;
import com.example.service.ExpenseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController // cannot be component, it has to be a controller
public class ExpenseDataLoader {

    private static List<Expense> expenses = new ArrayList<>();
    private final ExpenseService expenseService;

    public ExpenseDataLoader(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/expenses.json");

        expenses = mapper.readValue(is, new TypeReference<List<Expense>>() {
        });


    }
    @GetMapping("expenses/{id}")
    public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }


    public static List<Expense> getExpenses() {
        return expenses;
    }

}
