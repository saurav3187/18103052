import java.util.Scanner;

public class q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to get the hailstone sequence of it:");
        int number = sc.nextInt();

        if(number < 1) {
            System.out.print("Invalid input");
            return;
        }
        if(number == 1) {
            System.out.print("1");
            return;
        }

        while(number!=1) {
            System.out.print(number+" ");

            if(number%2==0)
            number = number/2;

            else
            number = number*3+1;
        }
        System.out.print("1");
    }
}