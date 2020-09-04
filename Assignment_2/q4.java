import java.util.Scanner;

public class q4 {

    public static void main(String[] args) {
        int i=1,sum=0;
        for(; i<Integer.MAX_VALUE && sum<Integer.MAX_VALUE; i++)
        {
            if(sum+i>=Integer.MAX_VALUE)
                break;
            sum+=i;
            if(sum==i*i)
                System.out.println("The smallest number : "+i);
        }
        System.out.println("The program ended at "+i);
    }
}