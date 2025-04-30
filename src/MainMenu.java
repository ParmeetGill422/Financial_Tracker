import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void show(){
        List<Transaction> transactions = TransactionFile.loadTransactions();
        boolean running = true;

        while (running){
            System.out.println("\n༺˚ʚ Home Menu ɞ˚༻");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "D" :
                    addTransaction(true);
                    break;
                case "P" :
                    addTransaction(false);
                    break;
                case "L" :
                    Ledger.ledgerMenu(transactions);
                    break;
                case "X" :
                    running = false;
                    break;
                default :
                    System.out.println("Not a option");
                    break;
            }

            transactions = TransactionFile.loadTransactions();
        }
    }
    private static void addTransaction (boolean isDeposit){
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (!isDeposit) amount = -amount;

        Transaction t = new Transaction(java.time.LocalDate.now(), java.time.LocalTime.now(), desc, vendor, amount);
        TransactionFile.saveTransaction(t);
        System.out.println("Transaction has been saved");
    }
}
