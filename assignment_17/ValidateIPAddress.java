import java.util.regex.*;

public class ValidateIPAddress {

    public static void main(String[] args) {
        // Each octet: 0-255
        // Breakdown: 0-199 | 200-249 | 250-255
        String octet = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String regex = "^" + octet + "\\." + octet + "\\." + octet + "\\." + octet + "$";

        String[] ipAddresses = {
            "192.168.1.1",    // Valid
            "255.255.255.255",// Valid
            "0.0.0.0",        // Valid
            "256.100.50.25",  // Invalid - 256 out of range
            "192.168.1",      // Invalid - only 3 octets
            "192.168.01.1",   // Valid
            "999.999.999.999" // Invalid
        };

        System.out.println("=== IPv4 Address Validation ===\n");

        for (String ip : ipAddresses) {
            boolean isValid = ip.matches(regex);
            System.out.println((isValid ? "✅" : "❌") + " \"" + ip + "\" → " + (isValid ? "Valid" : "Invalid"));
        }
    }
}
