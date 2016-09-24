package BankAccounts;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


    public class BankAccounts {
        Account account;
        AccountCreation accountcreation;
        App app;


        @Before
        public void Setup() {
            account = new Account("Kyle", "Checking", 1111);


        }

        @Test
        public void setBalance() {
            account.setBalance(100);
            int expectedValue = 100;
            int actualValue = account.getBalance();
            assertEquals("The expected value should be 100", expectedValue, actualValue);
        }

        @Test
        public void increaseBalance() {
            int startBalance = 0;
            int increaseAmount = 10;
            int expectedValue = startBalance + increaseAmount;
            account.increaseBalance(10);
            int actualValue = account.getBalance();
            assertEquals("The expected value should be 10", expectedValue, actualValue);
        }

        @Test
        public void decreaseBalance() {
            account.setBalance(20);
            account.decreaseBalance(10);
            int expectedValue = 10;
            int actualValue = account.getBalance();
            assertEquals("The expected value should be 10", expectedValue, actualValue);
        }
        @Test
        public void createAccount(){
            Account expectedValue = new Account("Kyle", "Checking", 1111);
            Account actualValue = accountcreation.createBankAccount("Kyle", "Checking", 1111);
            assertEquals("The expected Value is a new account", expectedValue, actualValue);
        }
    }




