package com.example.service;

import com.example.model.AppUser;
import com.example.model.Expense;
import com.example.repository.ExpenseRepository;
import com.example.utils.ExpenseDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


//@Profile("json") // This service will only be active when the 'json' profile is active
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    private static final AtomicLong idCounter = new AtomicLong(); //this will be used to generate unique IDs for expenses and use idCounter.incrementAndGet() to get the next ID

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }


    @Override
    public List<Expense> getAllUserExpenses(Long userId) {
        return List.of();
    }

    @Override
    public List<Expense> getExpenseByDay(String date,Long userId) {
        // Fix: Add () after stream, use .filter(), then collect to List
        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream()
                .filter(expense -> expense.getDate().equals(date))
                .toList();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month, Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream().filter(
                expense -> expense.getCategory().equalsIgnoreCase(category) && expense.getDate().startsWith(month)
        ).toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return List.of();
    }

    @Override
    public List<String> getAllExpenseCategories(Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream()
                .map(Expense::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }


//    @Override
//    public List<String> getAllExpenseCategories(Long userId) {
//        // Assumes you want unique list of categories
//        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream()
//                .map(Expense::getCategory)
//                .distinct()
//                .collect(Collectors.toList());
//    }




    @Override
    public Optional<Expense> getExpenseById(Long id,Long userId) {
        return expenseRepository.findByIdAndUserId(id,userId).stream().filter(
                expense -> expense.getId().equals(id)
        ).findFirst();
    }

    @Override
    public boolean updateExpense(Expense updatedExpense,Long userId) {
        Optional<Expense> existingExpense = expenseRepository.findByIdAndUserId(updatedExpense.getId(), userId);
        if (existingExpense.isPresent()) {
            updatedExpense.setUser(existingExpense.get().getUser());
            expenseRepository.save(updatedExpense);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id,Long userId) {
        Optional<Expense> existingExpense = expenseRepository.findByIdAndUserId(id,userId);
        if (existingExpense.isPresent()) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Expense addExpense(Expense expense,Long userId) {

        Optional<AppUser> userOptional  =
                userService.findUserById(userId);

        if(userOptional.isPresent()){
            AppUser user = userOptional.get();
            expense.setUser(user); // Set the user for the expense

            return expenseRepository.save(expense); // Return the added expense
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }


}
