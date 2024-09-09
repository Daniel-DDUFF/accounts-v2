package com.eazybytes.accounts.service;

import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    CustomerRepository customerRepository;

    // Anciennes Méthodes

    public List<Accounts> getAllAccounts() {
        List<Accounts> allAccounts = new ArrayList<>();
        accountsRepository.findAll().forEach(allAccounts::add);
        return allAccounts;
    }

    public Accounts getAccountsById(long id) {
        return accountsRepository.findById(id).orElse(null);
    }

    public String save(Accounts accounts) {
        int id = accounts.getCustomerId();
        if (customerRepository.existsById(id)) {
            accounts.setCreateDt(LocalDate.now());
            accountsRepository.save(accounts);
            return "saved";
        } else {
            return "failed, customer not found!";
        }
    }

    public String deleteAccount(Long id) {
        accountsRepository.deleteById(id);
        return "deleted!";
    }

    public String updateAccount(long id, Accounts updateAccounts) {
        Accounts accountsFind = accountsRepository.findById(id).orElse(null);
        if (accountsFind != null) {
            updateAccounts.setAccountNumber(id);
            updateAccounts.setCreateDt(LocalDate.now());
            accountsRepository.save(updateAccounts);
            return "update successful!";
        } else {
            return "account not found!";
        }
    }

    // Nouvelles Méthodes

    @Transactional
    public List<String> saveAll(List<Accounts> accountsList) {
        List<String> responseList = new ArrayList<>();
        for (Accounts account : accountsList) {
            try {
                accountsRepository.save(account);
                responseList.add("Compte enregistré avec succès : " + account.getAccountNumber());
            } catch (Exception e) {
                responseList.add("Échec d'enregistrement pour le compte : " + account.getAccountNumber() + " - Erreur : " + e.getMessage());
            }
        }
        return responseList;
    }

    @Transactional
    public String deleteAllAccounts() {
        try {
            accountsRepository.deleteAll();
            return "Tous les comptes ont été supprimés avec succès";
        } catch (Exception e) {
            return "Erreur lors de la suppression des comptes : " + e.getMessage();
        }
    }

    @Transactional
    public String deleteAllByIds(List<Long> accountIds) {
        try {
            accountsRepository.deleteAllByAccountNumberIn(accountIds);
            return "Comptes supprimés avec succès";
        } catch (Exception e) {
            return "Erreur lors de la suppression des comptes : " + e.getMessage();
        }
    }

    @Transactional
    public String updateAccounts(List<Accounts> accountsList) {
        try {
            accountsList.forEach(account -> accountsRepository.save(account));
            return "Comptes mis à jour avec succès";
        } catch (Exception e) {
            return "Erreur lors de la mise à jour des comptes : " + e.getMessage();
        }
    }

    public List<Accounts> findAllByIds(List<Long> accountIds) {
        return (List<Accounts>) accountsRepository.findAllById(accountIds);
    }
}
