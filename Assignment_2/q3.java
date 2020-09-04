import java.util.Scanner;

public class q3 {

    static int comp(String s1s, String s2s) {
        int n1 = s1s.length();
        int n2 = s2s.length();
        char[] s1 = s1s.toCharArray();
        char[] s2 = s2s.toCharArray();
        int cmp = 0;
        for(int i=0;i<n1 && i<n2;i++) {
            if(s1[i]<s2[i]) {
                cmp=-1;break;
            }
            if(s1[i]>s2[i]) {
                cmp=1;break;
            }
        }
        if(cmp==1 || cmp==-1) return cmp;
        if(cmp==0 && n1>n2) return 1;
        if(cmp==0 && n1<n2) return -1;
        return 0;
    }

    static void sortStr(String[] sr) {
        int n = sr.length;
        for(int i=0;i<n-1;i++) {
            String mn = sr[i];
            int ind = i;
            for(int j=i+1;j<n;j++) {
                if(comp(mn,sr[j]) == 1) {
                    ind = j;
                    mn = sr[j];
                }
            }
            sr[ind] = sr[i];
            sr[i] = mn;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of strings:");
        int n = sc.nextInt();
        String[] sr = new String[n];
        sc.nextLine();
        for(int i=0;i<n;i++) {
            sr[i] = sc.nextLine();
        }
        sortStr(sr);
        for(int i=0;i<n;i++) {
            System.out.print(sr[i]+" ");
        }
    }
}