import java.util.Scanner;

public class q2 {

    static void countSort(int[] ar) {
        int num = ar.length;
        int[] count = new int[21];
        for(int i=0;i<21;i++) count[i] = 0;
        for(int i=0;i<num;i++) {
            count[ar[i]]++;
        }
        int temp=0;
        for(int i=0;i<21;i++) {
            for(int j=0;j<count[i];j++) {
                ar[temp] = i;
                temp++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("COUNTING SORT (RANGE 0 TO 20)\n\nEnter number of elements:");
        int num = sc.nextInt();
        System.out.println("Enter the elements:");
        int[] ar = new int[num];
        for(int i=0;i<num;i++) {
            ar[i] = sc.nextInt();
        }
        countSort(ar);
        for(int i=0;i<num;i++) {
            System.out.print(ar[i]+" ");
        }
    }
}