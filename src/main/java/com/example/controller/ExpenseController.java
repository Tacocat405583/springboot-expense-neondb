package com.example.controller;

import com.example.model.AppUser;
import com.example.model.Expense;
import com.example.service.ExpenseService;
import com.example.service.UserService;
import com.example.utils.ExpenseDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    private final UserService userService;

    // Constructor injection for ExpenseService

    public ExpenseController(ExpenseService expenseService, UserService userService) {//we can fix
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping("/expenses/day/{date}")
    public ResponseEntity<List<Expense>> getExpenseByDay(@PathVariable String date, Authentication authentication) {

        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);
        List<Expense> expenses = expenseService.getExpenseByDay(date, user.getId());

        return ResponseEntity.ok(expenses);
    }
    @GetMapping("/expenses/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpenseByCategoryAndMonth(@PathVariable String category, @RequestParam String month,Authentication authentication){
        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);
        List<Expense> expenses = expenseService.getExpenseByCategoryAndMonth(category, month,user.getId());
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories(Authentication authentication) {

        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);
        List<String> categories = expenseService.getAllExpenseCategories(user.getId());
        //List<String> categories = expenseService.getAllExpenseCategories();

        if (categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Authentication authentication) {
        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);

        Expense addedExpense = expenseService.addExpense(expense,user.getId());
        return new ResponseEntity<>(new Expense(),HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@RequestBody Expense expense,Authentication authentication) {

        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);

        expense.setId(id);
        boolean isUpdated = expenseService.updateExpense(expense, user.getId());
        if(isUpdated){
            return new ResponseEntity<>(expense,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);

        boolean isDeleted = expenseService.deleteExpense(id,user.getId());
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName(); // Get the username from the authentication object
        AppUser user = userService.findUserByUsername(username);
        Expense expense = expenseService.getExpenseById(id, user.getId()).orElse(null);

        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
