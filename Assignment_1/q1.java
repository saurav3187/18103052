import java.util.Scanner;
public class q1
{
	public static void main(String[] args)
	{
		Scanner scan= new Scanner(System.in);
		String str1,str2;
		System.out.println(" Enter string 1 : ");
		str1=scan.nextLine();
		System.out.println("Enter string 2:");
		str2=scan.next();
		int e=0,f=0;
		int l1=str1.length();
		int l2=str2.length();
		if(l1<l2)
		System.out.println("0");
		else {
			int [] a;
			int [] b;
			a= new int[500];
			b=new int[500];
			for (int i=0;i<l2;i++)
				 b[(int)str2.charAt(i)]++;
			for(int i=0;i<l2;i++){

		          int l=(int)str1.charAt(i);
		          if(a[l]<b[l])
		            e++;
		          a[l]++;

		          if(e==l2)
		            f++;}

		          for(int i=l2;i<l1;i++){

		          int l=(int)str1.charAt(i);
		        int p=(int)str1.charAt(i-l2);

		        if(a[p]<=b[p])
		        e--;

		        a[p]--;

		        if(a[l]<b[l])
		        e++;

		        a[l]++;

		        if(e==l2)
		        f++;}

		        System.out.println(f);
		}

	}
}
