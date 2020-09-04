import java.util.Scanner;

public class q1 {
    static void cmp(String st1, String st2) {
        int num1 = st1.length();
        int num2 = st2.length();
        char[] s1 = st1.toCharArray();
        char[] s2 = st2.toCharArray();
        int temp = 0;
        for(int i=0;i<num1 && i<num2;i++) {
            if(s1[i]<s2[i]) {
                temp=-1;break;
            }
            if(s1[i]>s2[i]) {
                temp=1;break;
            }
        }
        if(temp==1)
        {
            System.out.println(st2+" < "+st1);
        }
        else if(temp==-1)
        {
            System.out.println(st1+" < "+st2);
        }
        else {
            if(num2>num1)
            {
                System.out.println(st1+" < "+st2);
            }
            else if(num1>num2)
            {
                System.out.println(st2+" < "+st1);
            }
            else
            {
                System.out.println(st1+" = "+st2);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the two strings:");
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        cmp(s1,s2);
    }
}