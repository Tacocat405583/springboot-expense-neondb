package com.example.repository;

import com.example.model.AppUser;
import com.example.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // This interface will automatically provide CRUD operations for Expense entities
    // No additional methods are needed unless specific queries are required


    //7/21/2025

    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    Optional<Expense> findByIdAndUserId(Long id, Long userId);

    Long user(AppUser user);


}
