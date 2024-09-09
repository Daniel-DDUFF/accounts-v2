package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    // Anciennes Méthodes

    @Operation(summary = "Get account by ID")
    @GetMapping("/myAccount/{id}")
    public Accounts getAccountDetails(@PathVariable("id") Long id) {
        return accountsService.getAccountsById(id);
    }

    @Operation(summary = "Get all accounts")
    @GetMapping("/accounts")
    public List<Accounts> getAllAccounts() {
        return accountsService.getAllAccounts();
    }

    @Operation(summary = "Create a new account")
    @PostMapping("/newAccount")
    public String newAccount(@RequestBody Accounts accounts) {
        return accountsService.save(accounts);
    }

    @Operation(summary = "Update an account by ID")
    @PutMapping("/update/{id}")
    public String updateAccount(@PathVariable("id") Long id, @RequestBody Accounts updateAccounts) {
        return accountsService.updateAccount(id, updateAccounts);
    }

    @Operation(summary = "Delete an account by ID")
    @DeleteMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        return accountsService.deleteAccount(id);
    }

    // Nouvelles Méthodes

    @Operation(summary = "Create multiple accounts")
    @PostMapping("/newAccounts")
    public List<String> newAccounts(@RequestBody List<Accounts> accountsList) {
        return accountsService.saveAll(accountsList);
    }

    @Operation(summary = "Delete all accounts")
    @DeleteMapping("/deleteAllAccounts")
    public String deleteAllAccounts() {
        return accountsService.deleteAllAccounts();
    }

    @Operation(summary = "Delete multiple accounts by IDs")
    @DeleteMapping("/deleteAccounts")
    public String deleteAccounts(@RequestBody List<Long> accountIds) {
        return accountsService.deleteAllByIds(accountIds);
    }

    @Operation(summary = "Update multiple accounts")
    @PutMapping("/updateAccounts")
    public String updateAccounts(@RequestBody List<Accounts> accountsList) {
        return accountsService.updateAccounts(accountsList);
    }

    @Operation(summary = "Find accounts by IDs")
    @PostMapping("/findAccounts")
    public List<Accounts> findAccounts(@RequestBody List<Long> accountIds) {
        return accountsService.findAllByIds(accountIds);
    }
}