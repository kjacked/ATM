package BankAccounts;

import java.util.ArrayList;

public class AccountCreation {
	private ArrayList<Account> bankAccounts = new ArrayList<Account>();
	private String[] accountTypes = { "CHECKING", "SAVINGS", "INVESTMENT" };
	private String[] userCommands = { "NEW", "LOGIN" };

	public AccountCreation() {
		System.out.println("Welcome to the account creation menu.");
	}

	/**
	 * evaluate user input & execute respective behavior
	 */
	public void evaluateUserCommand() {
		String userCommand = null;
		while (!"QUIT".equals(userCommand)) {
			userCommand = getValidUserCommand().toUpperCase();
			switch (userCommand) {
			case "NEW":
				String accountHolder = getNewAccountHolder();
				String accountType = getValidAccountType();
				int validPin = getValidPin();

				createBankAccount(accountHolder, accountType, validPin);
				break;

			case "LOGIN":
				getValidAccount().login();
				break;
			}
		}
	}

	/**
	 * Display list of all account holders
	 */
	public void displayAccountHolders() {
		System.out.println("Displaying a list of valid account holders...");
		for (Account account : bankAccounts) {
			System.out.println(account.getAccountHolder());
		}
	}

	/**
	 * Continually request user input until a valid account is entered.
	 */
	public Account getValidAccount() {
		Account bankAccount = null;
		if (bankAccounts.size() < 1) {
			System.out.println("Try creating at least one account first!");

			String accountHolder = getNewAccountHolder();
			String accountType = getValidAccountType();
			int validPin = getValidPin();

			bankAccount = createBankAccount(accountHolder, accountType, validPin);
		}

		while (bankAccount == null) {
			displayAccountHolders();
			String userSelection = User.stringInput("Enter the existing account holder to log into. ");
			for (Account account : bankAccounts) {
				String accountHolder = account.getAccountHolder();
				if (userSelection.equals(accountHolder)) {
					bankAccount = account;
				}
			}
		}
		return bankAccount;
	}

	/**
	 * continually request account holder name until a valid entry, then return
	 * account holder name
	 */
	public String getNewAccountHolder() {
		String newAccountHolder = null;
		while (true) {
			newAccountHolder = User.stringInput("Enter the name of the new account holder. ");
			if (!hasAccount(newAccountHolder)) {
				break; // exit loop
			}
		}
		return newAccountHolder;
	}

	/**
	 * if value passed into this method already exists in list of bank accounts,
	 * then return true; the user has an existing account
	 */
	public boolean hasAccount(String accountHolder) {
		for (Account bankAccount : this.bankAccounts) {
			String currentAccountHolder = bankAccount.getAccountHolder();
			if (currentAccountHolder.equals(accountHolder)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * display valid types of accounts that can be create
	 */
	public void displayAccountTypes() {
		System.out.println("Below is a list of account types that can be created. ");
		for (String accountType : this.accountTypes) {
			System.out.println("\t" + accountType);
		}
	}

	/**
	 * First, display account types Second, continually request account type
	 * until a valid entry, then return account type
	 */
	public String getValidAccountType() {
		displayAccountTypes();
		String accountType = null;
		while (true) {
			accountType = User.stringInput("What kind of account would you like to create?");
			if (isValidAccountType(accountType)) {
				return accountType;
			}
		}
	}

	/**
	 * if value passed into this method is in list of account types, then return
	 * true; this is a valid accountType
	 */
	public boolean isValidAccountType(String checkAccountType) {
		for (String accountType : accountTypes) {
			if (checkAccountType.toUpperCase().equals(accountType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * continually request pin until a valid entry, then return pin
	 */
	public int getValidPin() {
		while (true) {
			Integer pin = User.integerInput("Enter a 4 digit pin");
			if (pin.toString().length() == 4) {
				return pin;
			}
		}
	}

	public void displayUserCommands() {
		System.out.println("Displaying a list of valid user commands...");
		for (String command : userCommands) {
			System.out.println("\t" + command);
		}
	}

	/**
	 * continually request a user command until a valid one is entered. return
	 * valid user command
	 */
	public String getValidUserCommand() {
		displayUserCommands();
		String userCommand = User.stringInput("Enter a command");
		while (!isValidUserCommand(userCommand)) {
			userCommand = User.stringInput("Enter a command");
		}
		return userCommand;
	}

	/**
	 * return true if the specified userCommand is in the list of user commands
	 */
	public boolean isValidUserCommand(String userCommand) {
		for (String command : this.userCommands) {
			if (command.equals(userCommand.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * create bank account with specified user, type, and pin
	 */
	public Account createBankAccount(String user, String type, int pin) {
		Account bankAccount = null;
		switch (type.toUpperCase()) {
		case "CHECKING":
			bankAccount = new Checking(user, pin);
			break;

		case "SAVINGS":
			bankAccount = new Savings(user, pin);
			break;

		case "INVESTMENT":
			bankAccount = new Investment(user, pin);
			break;
		}
		bankAccounts.add(bankAccount);
		return bankAccount;
	}

}