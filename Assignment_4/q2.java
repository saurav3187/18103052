import java.util.Scanner;

public class q2
{
    public static void main(String[] args)
    {
        int num1 = -1;
        byte b = (byte)num1; //Integer to byte: Range of byte: -2^7 to 2^7+1 because byte is signed.Therefore -1 remains -1.
        int num2 = b;
        char c1 = (char)b; //Byte to char: The byte is first converted to int and then int is converted to char.
        char c2 = (char)num2;
        int fnum1 = c1; //Char to int: char is of 16 bits whereas int is of 32 bits.So, the answer is 2^16 - value of char
        System.out.println("Initial int: "+ num1);
        System.out.println("Byte: "+ b + " " + num2 + " " + c2);
        System.out.println("Character: "+ c1);
        System.out.println("Final int: "+ fnum1);
    }
}
