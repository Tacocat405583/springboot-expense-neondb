package com.example.service;

import com.example.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getExpenseByDay(String day);
    List<Expense> getExpenseByCategoryAndMonth(String category,String month);
    List<String> getAllExpenseCategories();
    Optional<Expense> getExpenseById(Long id); // New method to get an expense by ID
    Expense addExpense(Expense expense);
    boolean updateExpense(Expense expense);
    boolean deleteExpense(Long expense);
}
