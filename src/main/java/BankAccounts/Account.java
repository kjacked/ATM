package BankAccounts;

import java.util.Random;

public class Account {
	private String[] commandList = { "BALANCE", "INFO", "INTEREST", "TRANSACTIONS", "DEPOSIT", "WITHDRAWAL", "QUIT" };
	private String username;
	public String accountStatus;
	public String accountType;
	private long accountNumber;
	public int accountPin;

	private int interestRate;
	private int balance;
	private int transactions;

	public Account(String username, String accountType, int pin) {
		this.username = username;
		this.accountType = accountType;
		this.accountNumber = System.nanoTime();
		this.accountPin = pin;
		this.balance = 0;
		this.interestRate = randInt(1, 3);
	}

	public void setBalance(int amount) {
		this.transactions++;
		this.balance = amount;
	}

	public String getAccountHolder() {
		return this.username;
	}

	public int getBalance() {
		return this.balance;
	}

	public void increaseBalance(int amount) {
		setBalance(getBalance() + amount);
	}

	public void decreaseBalance(int amount) {
		increaseBalance(amount * -1);
	}

	public int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public void evaluateCommand(String command) {
		System.out.println("");
		switch (command.toUpperCase()) {
		case "BALANCE":
			if (accountStatus == "OFACFreeze") {
				System.out.println("Balance unavailable, account Frozen!");
			} else {
				System.out.println("Your balance is " + getBalance());
			}
			break;

		case "INFO":
			System.out.println("Username = " + username);
			System.out.println("Status = " + accountStatus);
			System.out.println("Type = " + accountType);
			System.out.println("accountNum = " + accountNumber);
			System.out.println("Transactions = " + transactions);
			System.out.println("Interest Rate = " + interestRate);
			System.out.println("Balance = " + balance);
			break;

		case "TRANSACTIONS":
			if (transactions > 0) {
				System.out.println("Number of transactions = " + transactions);
			} else {
				System.out.println("No previous transactions!");
			}
			break;

		case "DEPOSIT":
			int depositAmount = User.integerInput("How much money would you like to deposit?");
			this.increaseBalance(depositAmount);
			break;

		case "INTEREST":
			System.out.println(interestRate);
			break;

		case "WITHDRAWAL":
			int withdrawalAmount = User.integerInput("How much money would you like to withdrawal?");
			if (withdrawalAmount > balance) {
				System.out.println("You have insufficient funds in your account!");
			} else {
				this.decreaseBalance(withdrawalAmount);
			}
			break;

		default:
			System.out.println("Invalid command!");
			break;
		}
	}

	public void displayWelcomeScreen() {
		System.out.println("Welcome to your banking home screen.");
		System.out.println("From here, you can enter commands to display desired information.");
		System.out.println("Below is a list of the command options.");
		for (String command : this.commandList) {
			// print({tab}, command)
			System.out.println("\t" + command);
		}
	}

	public void login() {
		int pin = User.integerInput("Please enter pin number");
		if (this.accountPin == pin) {
			displayWelcomeScreen();
			String input = User.stringInput("Enter a command: ");
			input = input.toUpperCase();
			while (!input.equals("QUIT")) {
				this.evaluateCommand(input);
				input = User.stringInput("Enter a command: ");
				input = input.toUpperCase();
			}
		}
	}
}
