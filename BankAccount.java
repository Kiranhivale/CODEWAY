import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000);
        ATM atmMachine = new ATM(userAccount);
        atmMachine.runATM();
    }

    @Override
    public String toString() {
        return "Main []";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Invalid deposit amount.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = scanner.nextDouble();
                if (userAccount.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.checkBalance());
                } else {
                    System.out.println("Withdrawal failed.");
                }
                break;

            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                if (userAccount.deposit(depositAmount)) {
                    System.out.println("Deposit successful. New balance: $" + userAccount.checkBalance());
                } else {
                    System.out.println("Deposit failed.");
                }
                break;

            case 3:
                System.out.println("Current balance: $" + userAccount.checkBalance());
                break;

            case 4:
                System.out.println("Exiting ATM. Thank you!");
                break;

            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public void runATM() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            try {
                System.out.print("Enter your choice (1-4): ");
                int option = scanner.nextInt();

                if (option >= 1 && option <= 4) {
                    performTransaction(option);
                    if (option == 4) {
                        break;
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
