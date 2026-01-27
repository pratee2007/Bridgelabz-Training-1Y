import java.util.Scanner;
public class Divisible{
    public static void main(String ... args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number");
        int number = sc.nextInt();
        if(number%5==0){
            System.out.print("it is dividible by 5");
        }else{
			System.out.print("Not divisible by 5");
		}
        

    }
}