package com.example.controller;

import com.example.model.Expense;
import com.example.service.ExpenseService;
import com.example.utils.ExpenseDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    // Constructor injection for ExpenseService

    public ExpenseController(ExpenseService expenseService) {//we can fix
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses/day/{date}")
    public ResponseEntity<List<Expense>> getExpenseByDay(@PathVariable String date){
        return ResponseEntity.ok(expenseService.getExpenseByDay(date));
    }
    @GetMapping("/expenses/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpenseByCategoryAndMonth(@PathVariable String category, @RequestParam String month){
        List<Expense> expenses = expenseService.getExpenseByCategoryAndMonth(category, month);
        if (expenses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories(){
        List<String> categories = ExpenseDataLoader.getExpenses()   // Get the list of all Expense objects
                .stream()                                               // Create a stream from the list to perform operations
                .map(Expense::getCategory)                              // Transform each Expense into its category (String)
                .distinct()                                            // Remove duplicate categories, keep only unique ones
                .toList();                         // Collect the unique categories back into a List<String>

        //List<String> categories = expenseService.getAllExpenseCategories();

        if (categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense addedExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(new Expense(),HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@RequestBody Expense expense){
        expense.setId(id);
        boolean isUpdated = expenseService.updateExpense(expense);
        if(isUpdated){
            return new ResponseEntity<>(expense,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        boolean isDeleted = expenseService.deleteExpense(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
