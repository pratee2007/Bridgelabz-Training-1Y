public class NestedTryExample {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};

        try {
            try {
                int value = arr[5];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid array index!");
            }

            int result = arr[1] / 0;

        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        }
    }
}
