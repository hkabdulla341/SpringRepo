package Practise.Utility;

public class Keyboard
{
    @SuppressWarnings("resource")
    public static String readString(String prompt)
    {
	System.out.print(prompt);
	return new java.util.Scanner(System.in).nextLine();
    }
}
