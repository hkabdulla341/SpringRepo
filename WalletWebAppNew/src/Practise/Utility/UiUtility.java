package Practise.Utility;

import java.util.ArrayList;

public class UiUtility
{
    public static char optionsDisplayer(ArrayList<String> option)
    {
	String alphas[] = { "a)", "b)", "c)", "d)", "e)", "f)", "g)", "h)", "i)", "j)" };
	String exitOpt = "z";

	String userOpt = "";
	char rt = ' ';
	boolean correctOption = false;

	while (!correctOption)
	{
	    System.out.println(option.get(0));

	    UiUtility.printLine((option.get(0).length()) + 10);

	    int optionAlpha = 0;
	    int optionSize = option.size() - 1;
	    for (int i = 1; i < optionSize; i++)
	    {
		System.out.println(alphas[optionAlpha] + option.get(i));
		++optionAlpha;
	    }

	    System.out.println(exitOpt + ")" + option.get(option.size() - 1));

	    boolean inputCorrect = false;
	    System.out.println("");

	    while (!inputCorrect)
	    {
		userOpt = Keyboard.readString("Your Option : ");

		if (userOpt.length() == 0)
		{
		    System.out.print("Error! No Input Detected! Re-Enter ");
		}
		else
		    inputCorrect = true;
	    }

	    char st = (alphas[0]).charAt(0);
	    char ls = (alphas[optionAlpha - 1]).charAt(0);

	    if (userOpt.length() > 1)
	    {
		System.out.println("Error: Detected a Sentence. Retry!\n");
	    }
	    else
	    {
		userOpt = Character.toLowerCase(userOpt.charAt(0)) + "";

		if (!(userOpt.charAt(0) >= st && userOpt.charAt(0) <= ls) && userOpt.charAt(0) != exitOpt.charAt(0))
		{
		    System.out.println("Error: Invalid Option! Retry!\n");
		}
		else
		{
		    correctOption = true;
		    rt = userOpt.charAt(0);
		}
	    }
	}

	return rt;
    }

    public static void printLine(int num)
    {
	printLine(num, '-');
    }

    public static void printDoubleLine(int num)
    {
	printLine(num, '=');
    }

    public static void printLine(int num, char pattern)
    {
	for (int i = 0; i < num; i++)
	{
	    System.out.print(pattern);
	}
	System.out.println("");

    }
}
