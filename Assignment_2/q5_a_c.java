import java.util.Scanner;

public class q5_a_c {

    public static void intersection(int []a1, int[] a2) {
        int n1=a1.length;
        int n2=a2.length;
        int emptyflag=1;
        int[] ours=new int[Math.min(n1, n2)];
        int i1=0, i2=0, index=0;
        while(i1<n1 && i2<n2) {
            if (a1[i1] == a2[i2]) {
                ours[index] = a1[i1];
                index++;
                i1++;
                i2++;
                emptyflag = 0;
            }
            else if (a1[i1] < a2[i2]) {
                i1++;
            }
            else i2++;
        }
        System.out.print("\nIntersection is:");
        if(emptyflag==1)
            System.out.print("NULL");
        else
            for(int i=0; i<index; i++)
                System.out.print(" " + ours[i]);
    }

    public static void union(int []a1, int[] a2) {
        int n1=a1.length;
        int n2=a2.length;
        int[] ours=new int[n1+n2];
        int i1=0, i2=0, index=0;
        while(i1<n1 && i2<n2) {
            if(a1[i1]==a2[i2]) {
                ours[index++]=a1[i1++];
                i2++;
            }
            else if(a1[i1]<a2[i2]) {
                ours[index++]=a1[i1++];
            }
            else {
                ours[index++]=a2[i2++];
            }
        }
        while(i2<n2) {
            ours[index++] = a2[i2++];
        }
        while(i1<n1) {
            ours[index++]=a1[i1++];
        }
        System.out.print("\nUnion is:");
        for(int i=0; i<index; i++)
            System.out.print(" " + ours[i]);
    }

    public static void complement(int []a1, int []ref) {
        int n1=a1.length, index=0;
        int []ours=new int[11];
        int i=0, j=0, emptyflag=1;
        while(index<11 && i<n1) {
            if(a1[i]==ref[index]) {
                i++;
                index++;
            }
            else {
                ours[j++]=ref[index++];
                emptyflag=0;
            }
        }
        while(index<11) {
            ours[j++] = ref[index++];
            emptyflag = 0;
        }
        System.out.print("\nComplement is:");
        if(emptyflag==1)
            System.out.print("NULL");
        else
        for(i=0; i<j; i++)
            System.out.print(" " + ours[i]);
    }

    public static int[] finetune(int[] a1) {
        int n=a1.length;
        int ref[]= new int [11];
        for(int i=0; i<11; i++)
            ref[i]=0;
        for(int i=0; i<n; i++) {
            ref[a1[i]]++;
        }
        int index=0;
        for(int i=0; i<11; i++) {
            if(ref[i]!=0) {
                a1[index]=i;
                index++;
            }
        }
        int []ours= new int [index];

        for(int i=0; i<index; i++)
            ours[i]=a1[i];
        return ours;
    }

    public static void main(String[] args) {

        Scanner obj = new Scanner (System.in);
        System.out.println("No. of elements in first set:");
        int n1=obj.nextInt();
        System.out.println("No. of elements in second set:");
        int n2=obj.nextInt(), count=0;
        int ref[]={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int []arr1= new int[n1];
        int []arr2= new int[n2];
        System.out.println("Elements of first set:");
        for(int i=0; i<n1; i++) {
            arr1[i] = obj.nextInt();
            while(arr1[i]<0 || arr1[i]>10) {
                System.out.println("Not a valid choice. Enter a number between 0 and 10");
                arr1[i]=obj.nextInt();
            }
        }
        System.out.println("Elements of second set:");
        for(int i=0; i<n2; i++) {
            arr2[i] = obj.nextInt();
            while(arr2[i]<0 || arr2[i]>10) {
                System.out.println("Not a valid choice. Enter a number between 0 and 10");
                arr2[i]=obj.nextInt();
            }
        }
		long start = System.currentTimeMillis();
        arr1=finetune(arr1);
        arr2=finetune(arr2);
        union(arr1, arr2);
        intersection(arr1, arr2);
        complement(arr1, ref);
        complement(arr2, ref);
		long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" milliseconds");
    }
}
