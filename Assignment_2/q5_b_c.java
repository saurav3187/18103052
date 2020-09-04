import java.util.*;

public class q5_b_c {

    public static void main(String[] args) {

        Set<Integer> s1=new HashSet<Integer>();
        Set<Integer> s2=new HashSet<Integer>();
        Set<Integer> ref=new HashSet<Integer>();
        ref.addAll(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        Scanner obj= new Scanner(System.in);
        System.out.println("No. of elements in first set:");
        int n1=obj.nextInt();
        System.out.println("No. of elements in second set:");
        int n2=obj.nextInt();
        System.out.println("Elements of first set:");
        for(int i=0; i<n1; i++) {
            int x=obj.nextInt();
            while(x<0 || x>10) {
                System.out.println("Not a valid choice. Enter a number between 0 and 10");
                x=obj.nextInt();
            }
            s1.add(x);
        }
        System.out.println("Elements of second set:");
        for(int i=0; i<n2; i++) {
            int x=obj.nextInt();
            while(x<0 || x>10) {
                System.out.println("Not a valid choice. Enter a number between 0 and 10");
                x=obj.nextInt();
            }
            s2.add(x);
        }
		long start = System.currentTimeMillis();
        Set<Integer> orion=new HashSet<Integer>(s1);
        orion.addAll(s2);
        System.out.println("Union: "+orion);
        Set<Integer> inte= new HashSet<Integer>(s1);
        inte.retainAll(s2);
        System.out.println("Intersection: "+inte);
        Set<Integer> comps1= new HashSet<Integer>(ref);
        comps1.removeAll(s1);
        System.out.println("Complement of set 1: "+comps1);
        Set<Integer> comps2= new HashSet<Integer>(ref);
        comps2.removeAll(s2);
        System.out.println("Complement of set 2: "+comps2);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" milliseconds");
    }
}
