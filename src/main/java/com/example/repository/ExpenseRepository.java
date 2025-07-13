package com.example.repository;

import com.example.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // This interface will automatically provide CRUD operations for Expense entities
    // No additional methods are needed unless specific queries are required
}
