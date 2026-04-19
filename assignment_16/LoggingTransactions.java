import java.util.*;
import java.time.LocalDateTime;

public class LoggingTransactions {

    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TXN10023",
            "TXN10024",
            "TXN10025",
            "TXN10026",
            "TXN10027",
            "TXN10028",
            "TXN10029"
        );

        System.out.println("=== Transaction Log ===\n");

        // forEach to log each transaction with a timestamp
        transactionIds.forEach(id ->
            System.out.println(LocalDateTime.now() + " - Transaction: " + id)
        );

        System.out.println("\nTotal transactions logged: " + transactionIds.size());
    }
}
