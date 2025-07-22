
//Couldnt get this to work, get it to work if you want a h2 database

//package com.example.service;
//
//import com.example.model.Expense;
//import com.example.repository.ExpenseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@Profile("db") // This service will only be active when the 'db' profile is active, look in the yaml file
////spring:
////profiles:
////active: db
//public class ExpenseServiceImplDb implements ExpenseService{
//
//    private final ExpenseRepository expenseRepository;
//
//    @Autowired //we can use constructor injection to inject the repository
//    public ExpenseServiceImplDb(ExpenseRepository expenseRepository) {
//        this.expenseRepository = expenseRepository;
//    }
//
//
//    @Override
//    public List<Expense> getExpenseByDay(String day) {
//        return expenseRepository.findAll().stream().filter(
//                expense -> expense.getDate().equalsIgnoreCase(day)).toList();
//
//    }
//
//    @Override
//    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
//        return expenseRepository.findAll().stream().filter(
//                expense -> expense.getCategory().equalsIgnoreCase(category) && expense.getDate().startsWith(month)).toList();
//
//    }
//
//    @Override
//    public List<String> getAllExpenseCategories() {
//        return expenseRepository.findAll().stream().map(
//                Expense::getCategory
//        ).distinct().toList(
//        );
//    }
//
//    @Override
//    public Optional<Expense> getExpenseById(Long id) {
//        return expenseRepository.findById(id);
//    }
//
//    @Override
//    public Expense addExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }
//
//    @Override
//    public boolean updateExpense(Expense updatedexpense) {
//        if(expenseRepository.existsById(updatedexpense.getId())){
//            expenseRepository.save(updatedexpense);
//            return true;
//        } else {
//            return false; // or throw an exception if you prefer
//        }
//    }
//
//    @Override
//    public boolean deleteExpense(Long id) {
//        if(expenseRepository.existsById(id)){
//            expenseRepository.deleteById(id);
//            return true;
//        } else {
//            return false; // or throw an exception if you prefer
//        }
//    }
//}
