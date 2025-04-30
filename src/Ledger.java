import java.util.List;
import java.util.Scanner;

public class Ledger {
    public static void displayAll(List<Trnasaction> trnasactions){
        System.out.println("\n--- All Transactions ---");
        trnasactions.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(t -> System.out.println(t.prettyPrint()));
    }
    public static void displayDeposits(List<Transaction> transactions){
        System.out.println("\n--- Deposit ---");
        transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(t -> System.out.println(t.prettyPrint()));
    }
    public static void displayPayments(List<Transaction> transactions) {
        System.out.println("\n--- Payments ---");
        transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(t -> System.out.println(t.prettyPrint()));
    }
    public static void ledgerMenu(List<Transaction> transactions){
        boolean viewing = true;
        Scanner scanner = new Scanner(System.in);

        while (viewing){
            System.out.println("\n༺˚ʚ Ledger Menu ɞ˚༻");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("Choice: ");
            String option = scanner.nextLine().trim().toUpperCase();

            switch (option){
                case "A" -> displayAll(transactions);
                case "D" -> displayDeposits(transactions);
                case "P" -> displayPayments(transactions);
                case "R" -> Reports.showReports(transactions);
                case "H" -> viewing = false;
                default -> System.out.println("Not a option");
            }
        }
    }
}
