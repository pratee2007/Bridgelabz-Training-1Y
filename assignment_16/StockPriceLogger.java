import java.util.*;
import java.time.LocalTime;

public class StockPriceLogger {

    static class Stock {
        String symbol;
        double price;
        double change;

        Stock(String symbol, double price, double change) {
            this.symbol = symbol;
            this.price = price;
            this.change = change;
        }
    }

    public static void main(String[] args) {
        List<Stock> liveFeed = Arrays.asList(
            new Stock("RELIANCE", 2875.50, +12.30),
            new Stock("TCS", 3945.00, -8.75),
            new Stock("INFY", 1456.20, +5.60),
            new Stock("HDFC", 1623.80, -3.20),
            new Stock("WIPRO", 478.90, +2.15),
            new Stock("ONGC", 189.45, -1.05),
            new Stock("ITC", 432.10, +0.80)
        );

        System.out.println("=== Live Stock Price Feed | Time: " + LocalTime.now() + " ===");
        System.out.println("----------------------------------------------------------");

        // forEach to print each stock update
        liveFeed.forEach(stock -> {
            String direction = stock.change >= 0 ? "▲" : "▼";
            System.out.printf("%-12s ₹%8.2f  %s %.2f%n",
                stock.symbol, stock.price, direction, stock.change);
        });

        System.out.println("----------------------------------------------------------");
        System.out.println("Total stocks tracked: " + liveFeed.size());
    }
}
