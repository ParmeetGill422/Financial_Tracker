import java.io.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionFile {
    private static final String FILE_NAME = "transactions.csv";

    public static List<Transaction> loadTransactions(){
        List<Transaction> transactions = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("\\|");
                if (parts.length == 5){
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction t = new Transaction(date, time, description, vendor, amount);
                    transactions.add(t);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("No existing transactions found. Starting fresh.");
        }catch (IOException e){
            System.out.println("Error reading transaction file.");
        }
        return transactions;
    }

    public static void saveTransaction(Transaction t){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))){
            writer.write(t.toString());
            writer.newLine();
        }catch (IOException e){
            System.out.println("Error saving transaction.");
        }
    }
}
