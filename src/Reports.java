import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Reports {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showReports(List<Transaction> transactions){
        boolean viewing =  true;
        while (viewing){
            System.out.println("\n༺˚ʚ Report Menu ɞ˚༻");
            System.out.println("1) Month-To-Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year-To-Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" :
                    monthToDate(transactions);
                    break;
                case "2" :
                    previousMonth(transactions);
                    break;
                case "3" :
                    yearToDate(transactions);
                    break;
                case "4" :
                    previousYear(transactions);
                    break;
                case "5" :
                    searchByVendor(transactions);
                    break;
                case "6" :
                    customSearch(transactions);
                    break;
                case "0" :
                    viewing = false;
                    break;
                default :
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
    private static void monthToDate(List<Transaction> transactions){
        LocalDate now = LocalDate.now();
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions){
            if (t.getDate().getYear() == now.getYear() && t.getDate().getMonth() == now.getMonth()){
                result.add(t);
            }
        }
        display(result, "Month-To-Date Transactions");
    }
    private static void previousMonth(List<Transaction> transactions) {
        LocalDate now = LocalDate.now().minusMonths(1);
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == now.getYear() && t.getDate().getMonth() == now.getMonth()) {
                result.add(t);
            }
        }
        display(result, "Previous Month Transactions");
    }
    private static void yearToDate(List<Transaction> transactions) {
        int year = LocalDate.now().getYear();
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year) {
                result.add(t);
            }
        }
        display(result, "Year-To-Date Transactions");
    }
    private static void previousYear(List<Transaction> transactions){
        int prevYear = LocalDate.now().getYear() - 1;
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions){
            if (t.getDate().getYear() == prevYear){
                result.add(t);
            }
        }
        display(result, "Previous Years Transactions");
    }

    private static void searchByVendor(List<Transaction> transactions){
        System.out.print("Enter vendor name: ");
        String input = scanner.nextLine().toLowerCase();
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions){
            if (t.getVendor().toLowerCase().contains(input)){
                result.add(t);
            }
        }
        display(result, "Search by Vendor: " + input);
    }
    private static void customSearch(List<Transaction> transactions){
        System.out.print("Start date (YYYY-MM-DD) or press Enter to skip: ");
        String startInput = scanner.nextLine();
        System.out.print("End date (YYYY-MM-DD) or press Enter to skip: ");
        String endInput = scanner.nextLine();
        System.out.print("Vendor or press Enter to skip: ");
        String vendorInput = scanner.nextLine().toLowerCase();
        System.out.print("Description or press Enter to skip: ");
        String descInput = scanner.nextLine().toLowerCase();

        LocalDate start = startInput.isEmpty() ? null : LocalDate.parse(startInput);
        LocalDate end = endInput.isEmpty() ? null : LocalDate.parse(endInput);

        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions){
            boolean matches = true;

            if (start != null && t.getDate().isBefore(start)) matches = false;
            if (end != null && t.getDate().isAfter(end)) matches = false;
            if (!vendorInput.isEmpty() && !t.getVendor().toLowerCase().contains(vendorInput)) matches = false;
            if (!descInput.isEmpty() && !t.getDescription().toLowerCase().contains(descInput)) matches = false;

            if (matches) {
                result.add(t);}
        }
        display(result, "Custom Search Results");
    }
    private static void display(List<Transaction> transactions, String title){
        System.out.println("\n--- " + title + " ---");
        if (transactions.isEmpty()){
            System.out.println("No transactions found.");
        } else {
            List<Transaction> sorted = sortDescending(transactions);
            for (Transaction t : sorted) {
                System.out.println(t.prettyPrint());
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
