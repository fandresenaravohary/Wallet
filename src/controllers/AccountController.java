package controllers;

import models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private List<Account> accounts;

    public AccountController() {
        this.accounts = new ArrayList<>();

        accounts.add(new Account(1, "user1", "password1", "user1@example.com", 100.0));
        accounts.add(new Account(2, "user2", "password2", "user2@example.com", 150.0));
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public Account getAccountById(int id) {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void createAccount(Account account) {
        accounts.add(account);
    }

    public void updateAccount(Account updatedAccount) {
        accounts.stream()
                .filter(account -> account.getId() == updatedAccount.getId())
                .forEach(account -> account.setBalance(updatedAccount.getBalance()));
    }

    public void deleteAccount(int id) {
        accounts.removeIf(account -> account.getId() == id);
    }

    public static void main(String[] args) {
        AccountController accountController = new AccountController();

        System.out.println("All account :");
        accountController.getAllAccounts().forEach(System.out::println);

        int accountIdToFind = 1;
        System.out.println("\nAccount by ID " + accountIdToFind + " :");
        Account foundAccount = accountController.getAccountById(accountIdToFind);
        System.out.println(foundAccount);

        Account newAccount = new Account(3, "user3", "password3", "user3@example.com", 200.0);
        accountController.createAccount(newAccount);
        System.out.println("\nNew Account created :");
        accountController.getAllAccounts().forEach(System.out::println);

        int accountIdToUpdate = 2;
        Account updatedAccount = accountController.getAccountById(accountIdToUpdate);
        if (updatedAccount != null) {
            updatedAccount.setBalance(180.0);
            accountController.updateAccount(updatedAccount);
            System.out.println("\nAccount updated :");
            accountController.getAllAccounts().forEach(System.out::println);
        }

        int accountIdToDelete = 1;
        accountController.deleteAccount(accountIdToDelete);
        System.out.println("\nAccount deleted :");
        accountController.getAllAccounts().forEach(System.out::println);
    }
}