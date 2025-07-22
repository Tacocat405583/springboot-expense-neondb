package com.example.service;

import com.example.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getAllUserExpenses(Long userId);
    List<Expense> getExpenseByDay(String day, Long userId);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month, Long userId);

    List<String> getAllExpenseCategories();              // Optional - for all users
    List<String> getAllExpenseCategories(Long userId);   // ✅ This is the one you're using

    Optional<Expense> getExpenseById(Long id, Long userId);
    Expense addExpense(Expense expense, Long userId);
    boolean updateExpense(Expense expense, Long userId);
    boolean deleteExpense(Long expense, Long userId);
}

