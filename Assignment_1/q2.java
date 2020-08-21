import java.util.Scanner;
public class q2{
	public static void main(String[] args) {
		System.out.println("Enter the string:");
		Scanner scan=new Scanner(System.in);
		String p;
		p=scan.nextLine();
		System.out.println("Enter the size of replacement vector:");
		int n=scan.nextInt();
		int x=0;
		String[] s=new String[500];
		String[] s1=new String[n];
		String[] s2=new String[n];
		String temp="";
		java.util.HashMap<String, Integer> mark = new java.util.HashMap<String, Integer>();
		for(int i=0;i<n;i++){
	        System.out.println("Enter word to be replaced: ");
	        s1[i]=scan.next();
	        s2[i]=String.valueOf(s1[i].charAt(0));
	        for(int r=0;r<s1[i].length()-1; r++) {
	        	s2[i]=s2[i]+'*';
	        }
	        mark.put(s1[i],i+1);}

	        int k=p.length();

	        for(int i=0;i<k;i++){
	        int j=i;
	        temp="";

	        while((j<k)&&(p.charAt(j)!=' ')){
	        temp=temp+p.charAt(j);
	        j++;}

	        if(temp!=" ")
	        i=j;

	        s[x]=temp;
	        x++;}

	        for(int i=0;i<x;i++){

	        if(mark.containsKey(s[i])){
	        int z=mark.get(s[i]);
	        s[i]=s2[z-1];}}

	        for(int i=0;i<x;i++)
	        System.out.print(s[i]+" ");

	        System.out.print("\n");


	}
}