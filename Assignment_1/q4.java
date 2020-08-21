import java.util.Scanner;
class q4{
	static boolean areAnagram(String s1, String s2) {
		if (s1.length()!=s2.length()) {
			return false;
		}
		int value=0;
		for (int i=0;i<s1.length();i++) {
			value=value^(int)s1.charAt(i);
			value=value^(int)s2.charAt(i);
			}
		return value==0;
	}
	public static void main(String[] args) {
		String s3,s4;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter string 1:");
		s3=sc.nextLine();
		System.out.println("Enter string 2:");
		s4=sc.nextLine();
		boolean answer=areAnagram(s3,s4);
		if(answer==true)
			System.out.println("Strings are anagrams");
		else
			System.out.println("Strings are not anagrams");
	}
}
