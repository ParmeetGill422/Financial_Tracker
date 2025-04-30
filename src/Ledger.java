import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Ledger {

    public static void displayAll(List<Transaction> transactions) {
        System.out.println("\n--- All Transactions ---");
        List<Transaction> sorted = sortDescending(transactions);
        for (Transaction t : sorted) {
            System.out.println(t.prettyPrint());
        }
    }

    public static void displayDeposits(List<Transaction> transactions) {
        System.out.println("\n--- Deposits ---");
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        List<Transaction> sorted = sortDescending(deposits);
        for (Transaction t : sorted) {
            System.out.println(t.prettyPrint());
        }
    }

    public static void displayPayments(List<Transaction> transactions) {
        System.out.println("\n--- Payments ---");
        List<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        List<Transaction> sorted = sortDescending(payments);
        for (Transaction t : sorted) {
            System.out.println(t.prettyPrint());
        }
    }

    public static void ledgerMenu(List<Transaction> transactions) {
        boolean viewing = true;
        Scanner scanner = new Scanner(System.in);

        while (viewing) {
            System.out.println("\n༺˚ʚ Ledger Menu ɞ˚༻");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choice: ");
            String option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "A" :
                    displayAll(transactions);
                    break;
                case "D" :
                    displayDeposits(transactions);
                    break;
                case "P" :
                    displayPayments(transactions);
                    break;
                case "R" :
                    Reports.showReports(transactions);
                    break;
                case "H" :
                    viewing = false;
                    break;
                default :
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static List<Transaction> sortDescending(List<Transaction> list) {
        List<Transaction> copy = new ArrayList<>(list);
        for (int i = 0; i < copy.size() - 1; i++) {
            for (int j = 0; j < copy.size() - i - 1; j++) {
                if (copy.get(j).getDate().isBefore(copy.get(j + 1).getDate())) {
                    Transaction temp = copy.get(j);
                    copy.set(j, copy.get(j + 1));
                    copy.set(j + 1, temp);
                }
            }
        }
        return copy;
    }
}
