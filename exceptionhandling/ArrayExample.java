public class ArrayExample {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};

        try {
            int value = arr[5];
            System.out.println(value);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index!");

        } catch (NullPointerException e) {
            System.out.println("Array is not initialized!");
        }
    }
}