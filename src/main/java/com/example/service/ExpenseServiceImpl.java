package com.example.service;

import com.example.model.Expense;
import com.example.utils.ExpenseDataLoader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Profile("json") // This service will only be active when the 'json' profile is active
public class ExpenseServiceImpl implements ExpenseService {

    private static final AtomicLong idCounter = new AtomicLong(); //this will be used to generate unique IDs for expenses and use idCounter.incrementAndGet() to get the next ID


    @Override
    public List<Expense> getExpenseByDay(String date) {
        // Fix: Add () after stream, use .filter(), then collect to List
        return ExpenseDataLoader.getExpenses().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenses().stream().filter(
                expense -> expense.getCategory().equalsIgnoreCase(category) && expense.getDate().startsWith(month)
        ).toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        // Assumes you want unique list of categories
        return ExpenseDataLoader.getExpenses().stream()
                .map(Expense::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return ExpenseDataLoader.getExpenses().stream().filter(
                expense -> expense.getId().equals(id)
        ).findFirst();
    }

    @Override
    public boolean updateExpense(Expense updatedExpense) {
        Optional<Expense> existingExpense = getExpenseById(updatedExpense.getId());
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenses().remove(existingExpense.get());
            ExpenseDataLoader.getExpenses().add(updatedExpense);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> existingExpense = getExpenseById(id);
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenses().remove(existingExpense.get());
            return true;
        }
        return false;
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setId(idCounter.incrementAndGet());
        ExpenseDataLoader.getExpenses().add(expense);
        return expense;
    }


}
